/*
 * Copyright (c) 1999-2004 Sourceforge JACOB Project.
 * All rights reserved. Originator: Dan Adler (http://danadler.com).
 * Get more information about JACOB at http://sourceforge.net/projects/jacob-project
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package io.github.yahocen.jacob.com;

/**
 * @version $Id$
 * @author joe
 * <p>
 * DispatchEvents wraps this class around any event handlers before making the
 * JNI call that sets up the link with EventProxy. This means that
 * EventProxy.cpp just calls invoke(String,Variant[]) against the instance of
 * this class. Then this class does reflection against the event listener to
 * call the actual event methods. The event methods can return void or return a
 * Variant. Any value returned will be passed back to the calling windows module
 * by the Jacob JNI layer.
 * <p>
 * 
 * The void returning signature is the standard legacy signature. The Variant
 * returning signature was added in 1.10 to support event handlers returning
 * values.
 * 
 */
public abstract class InvocationProxy {

	/**
	 * the object we will try and forward to.
	 */
	protected Object mTargetObject = null;

	/**
	 * dummy constructor for subclasses that don't actually wrap anything and
	 * just want to override the invoke() method
	 */
	protected InvocationProxy() {
		super();
	}

	/**
	 * The method actually invoked by EventProxy.cpp. The method name is
	 * calculated by the underlying JNI code from the MS windows Callback
	 * function name. The method is assumed to take an array of Variant objects.
	 * The method may return a Variant or be a void. Those are the only two
	 * options that will not blow up.
	 * <p>
	 * Subclasses that override this should make sure mTargetObject is not null
	 * before processing.
	 * 
	 * @param methodName
	 *            name of method in mTargetObject we will invoke
	 * @param targetParameters
	 *            Variant[] that is the single parameter to the method
	 * @return an object that will be returned to the com event caller
	 */
	public abstract Variant invoke(String methodName, Variant[] targetParameters);

	/**
	 * used by EventProxy.cpp to create variant objects in the right thread
	 * 
	 * @return Variant object that will be used by the COM layer
	 */
	public Variant getVariant() {
		return new VariantViaEvent();
	}

	/**
	 * Sets the target for this InvocationProxy.
	 * 
	 * @param pTargetObject the object that will receive the events
	 * @throws IllegalArgumentException
	 *             if target is not publicly accessible
	 */
	public void setTarget(Object pTargetObject) {
		if (JacobObject.isDebugEnabled()) {
			JacobObject.debug("InvocationProxy: setting target "
					+ pTargetObject);
		}
		// 不再要求目标类必须 public：事件回调方法本身是 public 即可，
		// 匿名内部类/包私有类（如 new ApplicationEventSink(){...}）同样可接收事件。
		// 反射调用侧（InvocationProxyAllVariants）对方法 setAccessible(true) 以绕过
		// 非 public 声明类的访问检查；JNI 层本就不受此限制。
		mTargetObject = pTargetObject;
	}

}
