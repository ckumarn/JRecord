/*
 * @Author Bruce Martin
 * Created on 6/09/2005
 *
 * Purpose: Binary Integer - Little Endian (low to high format)
 *
 * Changes
 * # Version 0.56 Bruce Martin 2007/01/16
 *   - use isPostive method (instead of positive variable)
 *
 * # Version 0.60 Bruce Martin 2007/02/16
 *   - Starting to seperate the Record package out from the RecordEditor
 *     so that it can be used seperately. So classes have been moved
 *     to the record package (ie RecordException + new Constant interface)
 *
 *
 */
package net.sf.JRecord.Types;

import net.sf.JRecord.Common.Conversion;
import net.sf.JRecord.Common.IFieldDetail;
import net.sf.JRecord.Common.RecordException;

/**
 * Type Binary Integer - Little Endian (low to high format)
 *
 * @author Bruce Martin
 *
 * @version 0.55
 */
public class TypeBinLittleEndian extends TypeNum {

	private final boolean positiveStorage;


    /**
     * Type Binary Integer (Litte Endian format)
     * <p>This class is the interface between the raw data in the file
     * and what is to be displayed on the screen for Little-Endian
     * binary integers.
     *
     * @param isPostive wether it is a positive integer
     */
    public TypeBinLittleEndian(final boolean isPositive) {
        super(false, true, true, isPositive, true);
        positiveStorage = isPositive;
    }


    /**
     * Type Binary Integer (Litte Endian format)
     * <p>This class is the interface between the raw data in the file
     * and what is to be displayed on the screen for Little-Endian
     * binary integers.
     *
     * @param isPostive wether it is a positive integer
     */
    public TypeBinLittleEndian(final boolean isPositive, boolean positiveStorage) {
        super(false, true, true, isPositive, true);
        this.positiveStorage = positiveStorage;
    }

    /**
     * @see net.sf.JRecord.Types.Type#getField(byte[], int, net.sf.JRecord.Common.FieldDetail)
     */
    public Object getField(byte[] record,
            			   final int position,
            			   final IFieldDetail field) {
	    int pos = position - 1;
	    int min = java.lang.Math.min(field.getEnd(), record.length);

        String s;

        if (positiveStorage) {
            s = Conversion.getPostiveBinary(record, pos, min);
        } else {
            s = Conversion.getBinaryInt(record, pos, min);
        }

        s = addDecimalPoint(s, field.getDecimal());

        return s;
    }


    /**
     * @see net.sf.JRecord.Types.Type#setField(byte[], int, net.sf.JRecord.Common.FieldDetail, java.lang.Object)
     */
    public byte[] setField(byte[] record,
              final int position,
			  final IFieldDetail field,
			  final Object value)
            throws RecordException {

		int pos = position - 1;
		int len = field.getLen();
        String val = value.toString();

        formatValueForRecord(field, val);

        Conversion.setBigIntLE(record,
                				   pos, len,
                				   getBigDecimal(field, val).toBigInteger(),
                				   positiveStorage);

        return record;
    }
}
