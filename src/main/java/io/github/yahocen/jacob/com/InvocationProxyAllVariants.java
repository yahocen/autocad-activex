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

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class acts as a proxy between the windows event callback mechanism and
 * the Java classes that are looking for events. It assumes that all of the Java
 * classes that are looking for events implement methods with the same names as
 * the windows events and that the implemented methods accept an array of
 * variant objects. The methods can return void or a Variant that will be
 * returned to the calling layer. All Event methods that will be recognized by
 * InvocationProxyAllEvents have the signature
 * <p>
 * <code> void eventMethodName(Variant[])</code> or
 * <code> Variant eventMethodName(Variant[])</code>
 */
@Slf4j
public class InvocationProxyAllVariants extends InvocationProxy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.github.yahocen.jacob.com.InvocationProxy#invoke(java.lang.String,
	 *      io.github.yahocen.jacob.com.Variant[])
	 */
	public Variant invoke(String methodName, Variant[] targetParameters) {
		Variant mVariantToBeReturned = null;
		if (mTargetObject == null) {
			if (JacobObject.isDebugEnabled()) {
				JacobObject.debug("InvocationProxy: received notification ("
						+ methodName + ") with no target set");
			}
			// structured programming guidlines say this return should not be up
			// here
			return null;
		}
		Class<?> targetClass = mTargetObject.getClass();
		if (methodName == null) {
			throw new IllegalArgumentException(
					"InvocationProxy: missing method name");
		}
		if (targetParameters == null) {
			throw new IllegalArgumentException(
					"InvocationProxy: missing Variant parameters");
		}
		try {
			if (JacobObject.isDebugEnabled()) {
				JacobObject.debug("InvocationProxy: trying to invoke "
						+ methodName + " on " + mTargetObject);
			}
			Method targetMethod;
			targetMethod = targetClass.getMethod(methodName,
                    Variant[].class);
            // protected classes can't be invoked against even if they
            // let you grab the method. you could do
            // targetMethod.setAccessible(true);
            // but that should be stopped by the security manager
            Object mReturnedByInvocation = null;
            mReturnedByInvocation = targetMethod.invoke(mTargetObject,
                    new Object[] { targetParameters });
            if (mReturnedByInvocation != null) {
				if (!(mReturnedByInvocation instanceof Variant)) {
					// could try and convert to Variant here.
					throw new IllegalArgumentException(
							"InvocationProxy: invokation of target method returned "
									+ "non-null non-variant object: "
									+ mReturnedByInvocation);
				} else {
					mVariantToBeReturned = (Variant) mReturnedByInvocation;
				}
			}
        } catch (SecurityException e) {
			// what causes this exception?
			log.error("Error invoking method", e);
		} catch (NoSuchMethodException e) {
			// this happens whenever the listener doesn't implement all the
			// methods
			if (JacobObject.isDebugEnabled()) {
				JacobObject.debug("InvocationProxy: listener (" + mTargetObject
						+ ") doesn't implement " + methodName);
			}
		} catch (IllegalArgumentException e) {
            log.error("InvocationProxy: bad arguments to method {}", methodName, e);
			// we can throw these inside the catch block so need to re-throw it
			throw e;
		} catch (IllegalAccessException e) {
			// can't access the method on the target instance for some reason
			if (JacobObject.isDebugEnabled()) {
				JacobObject
						.debug("InvocationProxy: probably tried to access public method on non public class"
								+ methodName);
			}
			log.error("Error invoking method", e);
		} catch (InvocationTargetException e) {
			// invocation of target method failed
			log.error("Error invoking method", e);
		}
		return mVariantToBeReturned;

	}
}
