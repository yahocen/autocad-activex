/**
 *
 */
package io.github.yahocen.jacob.com;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;

/**
 * A utility class used to convert between Java objects and Variants
 */
public final class VariantUtilities {
	private VariantUtilities() {
		// utility class with only static methods don't need constructors
	}

	/**
	 * Populates a variant object from a java object. This method attempts to
	 * figure out the appropriate Variant type
	 *
	 * @param targetVariant the variant to populate
	 * @param pValueObject the java object to convert to a variant
	 * @param fByRef - true if this is a byref variant
	 */
	static void populateVariant(Variant targetVariant,
                                Object pValueObject, boolean fByRef) {
        // Java 17 基线：用 instanceof 模式链代替 switch 模式匹配（21+），分支顺序与原实现一致
        if (pValueObject == null) {
            targetVariant.putEmpty();
        } else if (pValueObject instanceof Integer i) {
            if (fByRef) {
                targetVariant.putIntRef(i);
            } else {
                targetVariant.putInt(i);
            }
        } else if (pValueObject instanceof Short i) {
            if (fByRef) {
                targetVariant.putShortRef(i);
            } else {
                targetVariant.putShort(i);
            }
        } else if (pValueObject instanceof String s) {
            if (fByRef) {
                targetVariant.putStringRef(s);
            } else {
                targetVariant.putString(s);
            }
        } else if (pValueObject instanceof Boolean b) {
            if (fByRef) {
                targetVariant.putBooleanRef(b);
            } else {
                targetVariant.putBoolean(b);
            }
        } else if (pValueObject instanceof Double v) {
            if (fByRef) {
                targetVariant.putDoubleRef(v);
            } else {
                targetVariant.putDouble(v);
            }
        } else if (pValueObject instanceof Float v) {
            if (fByRef) {
                targetVariant.putFloatRef(v);
            } else {
                targetVariant.putFloat(v);
            }
        } else if (pValueObject instanceof BigDecimal bigDecimal) {
            if (fByRef) {
                targetVariant.putDecimalRef(bigDecimal);
            } else {
                targetVariant.putDecimal(bigDecimal);
            }
        } else if (pValueObject instanceof Byte b) {
            if (fByRef) {
                targetVariant.putByteRef(b);
            } else {
                targetVariant.putByte(b);
            }
        } else if (pValueObject instanceof Date date) {
            if (fByRef) {
                targetVariant.putDateRef(date);
            } else {
                targetVariant.putDate(date);
            }
        } else if (pValueObject instanceof Long l) {
            if (fByRef) {
                targetVariant.putLongRef(l);
            } else {
                targetVariant.putLong(l);
            }
        } else if (pValueObject instanceof Currency currency) {
            if (fByRef) {
                targetVariant.putCurrencyRef(currency);
            } else {
                targetVariant.putCurrency(currency);
            }
        } else if (pValueObject instanceof SafeArray safeArray) {
            if (fByRef) {
                targetVariant.putSafeArrayRef(safeArray);
            } else {
                targetVariant.putSafeArray(safeArray);
            }
        } else if (pValueObject instanceof Dispatch dispatch) {
            if (fByRef) {
                targetVariant.putDispatchRef(dispatch);
            } else {
                targetVariant.putDispatch(dispatch);
            }
        } else if (pValueObject instanceof Variant) {
            // newly added 1.12-pre6 to support VT_VARIANT
            targetVariant.putVariant(pValueObject);
        } else {
            // sourceforge patch 2171967
            // used to rely on coercion but sometimes crashed VM
            throw new NotImplementedException(
                    "populateVariant() not implemented for "
                            + pValueObject.getClass());
        }
	}

	/**
	 * Map arguments based on msdn documentation. This method relies on the
	 * variant constructor except for arrays.
	 *
	 * @param objectToBeMadeIntoVariant the object to be converted to a variant
	 * @return Variant that represents the object
	 */
	private static Variant objectToVariant(Object objectToBeMadeIntoVariant) {
		if (objectToBeMadeIntoVariant == null) {
			return new Variant();
		} else if (objectToBeMadeIntoVariant instanceof Variant) {
			// if a variant was passed in then be a slacker and just return it
			return (Variant) objectToBeMadeIntoVariant;
		} else if (objectToBeMadeIntoVariant.getClass().isArray()) {
			// automatically convert arrays using reflection
			// handle it differently based on the type of array
			// added primitive support sourceforge 2762275
			SafeArray sa = null;
			int len1 = Array.getLength(objectToBeMadeIntoVariant);
			Class<?> componentType = objectToBeMadeIntoVariant.getClass()
					.getComponentType();

			if (componentType.isArray()) { // array of arrays
				int max = 0;
				for (int i = 0; i < len1; i++) {
					Object e1 = Array.get(objectToBeMadeIntoVariant, i);
					int len2 = Array.getLength(e1);
					if (max < len2) {
						max = len2;
					}
				}
				sa = new SafeArray(Variant.VariantVariant, len1, max);
				for (int i = 0; i < len1; i++) {
					Object e1 = Array.get(objectToBeMadeIntoVariant, i);
					for (int j = 0; j < Array.getLength(e1); j++) {
						sa.setVariant(i, j, objectToVariant(Array.get(e1, j)));
					}
				}
			} else if (byte.class.equals(componentType)) {
				byte[] arr = (byte[]) objectToBeMadeIntoVariant;
				sa = new SafeArray(Variant.VariantByte, len1);
				for (int i = 0; i < len1; i++) {
					sa.setByte(i, arr[i]);
				}
			} else if (int.class.equals(componentType)) {
				int[] arr = (int[]) objectToBeMadeIntoVariant;
				sa = new SafeArray(Variant.VariantInt, len1);
				for (int i = 0; i < len1; i++) {
					sa.setInt(i, arr[i]);
				}
			} else if (double.class.equals(componentType)) {
				double[] arr = (double[]) objectToBeMadeIntoVariant;
				sa = new SafeArray(Variant.VariantDouble, len1);
				for (int i = 0; i < len1; i++) {
					sa.setDouble(i, arr[i]);
				}
			} else if (long.class.equals(componentType)) {
				long[] arr = (long[]) objectToBeMadeIntoVariant;
				sa = new SafeArray(Variant.VariantLongInt, len1);
				for (int i = 0; i < len1; i++) {
					sa.setLong(i, arr[i]);
				}
			} else {
				// array of object
				sa = new SafeArray(Variant.VariantVariant, len1);
				for (int i = 0; i < len1; i++) {
					sa.setVariant(i, objectToVariant(Array.get(
							objectToBeMadeIntoVariant, i)));
				}
			}
			Variant returnVariant = new Variant();
			populateVariant(returnVariant, sa, false);
			return returnVariant;
		} else {
			// rely on populateVariant to throw an exception if its an
			// invalid type
			Variant returnVariant = new Variant();
			populateVariant(returnVariant, objectToBeMadeIntoVariant, false);
			return returnVariant;
		}
	}

	/**
	 * converts an array of objects into an array of Variants by repeatedly
	 * calling obj2Variant(Object)
	 *
	 * @param arrayOfObjectsToBeConverted the array of objects to be converted
	 * @return Variant[]
	 */
	static Variant[] objectsToVariants(
            Object[] arrayOfObjectsToBeConverted) {
		if (arrayOfObjectsToBeConverted instanceof Variant[]) {
			// just return the passed in array if it is a Variant array
			return (Variant[]) arrayOfObjectsToBeConverted;
		} else {
			Variant[] vArg = new Variant[arrayOfObjectsToBeConverted.length];
			for (int i = 0; i < arrayOfObjectsToBeConverted.length; i++) {
				vArg[i] = objectToVariant(arrayOfObjectsToBeConverted[i]);
			}
			return vArg;
		}
	}

	/**
	 * Convert a JACOB Variant value to a Java object (type conversions).
	 * provided in Sourceforge feature cefRequest 959381. A fix was done to handle
	 * byRef bug report 1607878.
	 * <p>
	 * Unlike other toXXX() methods, it does not do a type conversion except for
	 * special data types (it shouldn't do any!)
	 * <p>
	 * Converts Variant.VariantArray types to SafeArrays
	 *
	 * @return Corresponding Java object of the type matching the Variant type.
	 * @throws IllegalStateException   if no underlying windows data structure
	 * @throws NotImplementedException if unsupported conversion is requested
	 * @throws JacobException          if the calculated result was a JacobObject
	 *                                 usually as a result of error
	 */
	static Object variantToObject(Variant sourceData) {
		Object result = null;

		short type = sourceData.getvt(); // variant type

		if ((type & Variant.VariantArray) == Variant.VariantArray) { // array
			// returned?
			SafeArray array = null;
			type = (short) (type - Variant.VariantArray);
			// From SF Bug 1840487
			// This did call toSafeArray(false) but that meant
			// this was the only variantToObject() that didn't have its own
			// copy of the data so you would end up with weird run time
			// errors after some GC. So now we just get stupid about it and
			// always make a copy just like toSafeArray() does.
			array = sourceData.toSafeArray();
			result = array;
		} else { // non-array object returned
			switch (type) {
				case Variant.VariantEmpty: // 0
				case Variant.VariantNull: // 1
					break;
				case Variant.VariantShort: // 2
					result = sourceData.getShort();
					break;
				case Variant.VariantShort | Variant.VariantByref: // 2
					result = sourceData.getShortRef();
					break;
				case Variant.VariantInt: // 3
					result = sourceData.getInt();
					break;
				case Variant.VariantInt | Variant.VariantByref: // 3
					result = sourceData.getIntRef();
					break;
				case Variant.VariantFloat: // 4
					result = sourceData.getFloat();
					break;
				case Variant.VariantFloat | Variant.VariantByref: // 4
					result = sourceData.getFloatRef();
					break;
				case Variant.VariantDouble: // 5
					result = sourceData.getDouble();
					break;
				case Variant.VariantDouble | Variant.VariantByref: // 5
					result = sourceData.getDoubleRef();
					break;
				case Variant.VariantCurrency: // 6
					result = sourceData.getCurrency();
					break;
				case Variant.VariantCurrency | Variant.VariantByref: // 6
					result = sourceData.getCurrencyRef();
					break;
				case Variant.VariantDate: // 7
					result = sourceData.getJavaDate();
					break;
				case Variant.VariantDate | Variant.VariantByref: // 7
					result = sourceData.getJavaDateRef();
					break;
				case Variant.VariantString: // 8
					result = sourceData.getString();
					break;
				case Variant.VariantString | Variant.VariantByref: // 8
					result = sourceData.getStringRef();
					break;
				case Variant.VariantDispatch: // 9
					result = sourceData.getDispatch();
					break;
				case Variant.VariantDispatch | Variant.VariantByref: // 9
					result = sourceData.getDispatchRef(); // Can dispatches even
					// be byRef?
					break;
				case Variant.VariantError: // 10
					result = new NotImplementedException(
							"toJavaObject() Not implemented for VariantError");
					break;
				case Variant.VariantBoolean: // 11
					result = sourceData.getBoolean();
					break;
				case Variant.VariantBoolean | Variant.VariantByref: // 11
					result = sourceData.getBooleanRef();
					break;
				case Variant.VariantVariant: // 12 they are always by ref
					result = new NotImplementedException(
							"toJavaObject() Not implemented for VariantVariant without ByRef");
					break;
				case Variant.VariantVariant | Variant.VariantByref: // 12
					result = sourceData.getVariant();
					break;
				case Variant.VariantObject: // 13
					result = new NotImplementedException(
							"toJavaObject() Not implemented for VariantObject");
					break;
				case Variant.VariantDecimal: // 14
					result = sourceData.getDecimal();
					break;
				case Variant.VariantDecimal | Variant.VariantByref: // 14
					result = sourceData.getDecimalRef();
					break;
				case Variant.VariantByte: // 17
					result = sourceData.getByte();
					break;
				case Variant.VariantByte | Variant.VariantByref: // 17
					result = sourceData.getByteRef();
					break;
				case Variant.VariantLongInt: // 20
					result = sourceData.getLong();
					break;
				case Variant.VariantLongInt | Variant.VariantByref: // 20
					result = sourceData.getLongRef();
					break;
				case Variant.VariantTypeMask: // 4095
					result = new NotImplementedException(
							"toJavaObject() Not implemented for VariantBstrBlob/VariantTypeMask");
					break;
				case Variant.VariantArray: // 8192
					result = new NotImplementedException(
							"toJavaObject() Not implemented for VariantArray");
					break;
				case Variant.VariantByref: // 16384
					result = new NotImplementedException(
							"toJavaObject() Not implemented for VariantByref");
					break;
				default:
					result = new NotImplementedException("Unknown return type: "
							+ type);
					// there was a "return result" here that caused defect 1602118
					// so it was removed
					break;
			}// switch (type)

			if (result instanceof JacobException) {
				throw (JacobException) result;
			}
		}

		return result;
	}// toJava()

	/**
	 * Verifies that we have a scale 0 &lt;= x &lt;= 28 and now more than 96 bits of
	 * data. The roundToMSDecimal method will attempt to adjust a BigDecimal to
	 * pass this set of tests
	 *
	 * @param in the BigDecimal to be validated
	 * @throws IllegalArgumentException if out of bounds
	 */
	static void validateDecimalScaleAndBits(BigDecimal in) {
		BigInteger allWordBigInt = in.unscaledValue();
		if (in.scale() > 28) {
			// should this cast to a string and call putStringRef()?
			throw new IllegalArgumentException(
					"VT_DECIMAL only supports a maximum scale of 28 and the passed"
							+ " in value has a scale of " + in.scale());
		} else if (in.scale() < 0) {
			// should this cast to a string and call putStringRef()?
			throw new IllegalArgumentException(
					"VT_DECIMAL only supports a minimum scale of 0 and the passed"
							+ " in value has a scale of " + in.scale());
		} else if (allWordBigInt.bitLength() > 12 * 8) {
			throw new IllegalArgumentException(
					"VT_DECIMAL supports a maximum of "
							+ 12
									* 8
							+ " bits not counting scale and the number passed in has "
							+ allWordBigInt.bitLength());

		}
		// no bounds problem to be handled
	}

	/**
	 * Largest possible number with scale set to 0
	 */
	private static final BigDecimal LARGEST_DECIMAL = new BigDecimal(
			new BigInteger("ffffffffffffffffffffffff", 16));
	/**
	 * Smallest possible number with scale set to 0. MS doesn't support negative
	 * scales like BigDecimal.
	 */
	private static final BigDecimal SMALLEST_DECIMAL = new BigDecimal(
			new BigInteger("ffffffffffffffffffffffff", 16).negate());

	/**
	 * Does any validation that couldn't have been fixed by rounding or scale
	 * modification.
	 *
	 * @param in The BigDecimal to be validated
	 * @throws IllegalArgumentException if the number is too large or too small or
	 *                                  null
	 */
	static void validateDecimalMinMax(BigDecimal in) {
		if (in == null) {
			throw new IllegalArgumentException(
					"null is not a supported Decimal value.");
		} else if (LARGEST_DECIMAL.compareTo(in) < 0) {
			throw new IllegalArgumentException(
					"Value too large for VT_DECIMAL data type:" + in.toString()
							+ " integer: " + in.toBigInteger().toString(16)
							+ " scale: " + in.scale());
		} else if (SMALLEST_DECIMAL.compareTo(in) > 0) {
			throw new IllegalArgumentException(
					"Value too small for VT_DECIMAL data type:" + in.toString()
							+ " integer: " + in.toBigInteger().toString(16)
							+ " scale: " + in.scale());
		}

	}

	/**
	 * Rounds the scale and bit length so that it will pass
	 * validateDecimalScaleBits(). Developers should call this method if they
	 * really want MS Decimal and don't want to lose precision.
	 * <p>
	 * Changing the scale on a number that can fit in an MS Decimal can change
	 * the number's representation enough that it will round to a number too
	 * large to be represented by an MS VT_DECIMAL
	 *
	 * @param sourceDecimal the BigDecimal to be rounded
	 * @return BigDecimal a new big decimal that was rounded to fit in an MS
	 *         VT_DECIMAL
	 */
	public static BigDecimal roundToMSDecimal(BigDecimal sourceDecimal) {
		BigInteger sourceDecimalIntComponent = sourceDecimal.unscaledValue();
		BigDecimal destinationDecimal = new BigDecimal(
				sourceDecimalIntComponent, sourceDecimal.scale());
		RoundingMode roundingModel = RoundingMode.HALF_UP;
		validateDecimalMinMax(destinationDecimal);
		// First limit the number of digits and then the precision.
		// Try and round to 29 digits because we can sometimes do that
		BigInteger allWordBigInt;
		allWordBigInt = destinationDecimal.unscaledValue();
		if (allWordBigInt.bitLength() > 96) {
			destinationDecimal = destinationDecimal.round(new MathContext(29));
			// see if 29 digits uses more than 96 bits
            // Dang. It was over 97 bits so shorten it one more digit to
            // stay <= 96 bits
            destinationDecimal = destinationDecimal.round(new MathContext(
                    28));
        }
		// the bit manipulations above may change the scale so do it afterwards
		// round the scale to the max MS can support
		if (destinationDecimal.scale() > 28) {
			destinationDecimal = destinationDecimal.setScale(28, roundingModel);
		}
		if (destinationDecimal.scale() < 0) {
			destinationDecimal = destinationDecimal.setScale(0, roundingModel);
		}
		return destinationDecimal;
	}
}
