/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the deedtype table.
 */
public class DeedtypePk implements Serializable
{
	protected int code;

	/** 
	 * This attribute represents whether the primitive attribute code is null.
	 */
	protected boolean codeNull;

	/** 
	 * Sets the value of code
	 */
	public void setCode(int code)
	{
		this.code = code;
	}

	/** 
	 * Gets the value of code
	 */
	public int getCode()
	{
		return code;
	}

	/**
	 * Method 'DeedtypePk'
	 * 
	 */
	public DeedtypePk()
	{
	}

	/**
	 * Method 'DeedtypePk'
	 * 
	 * @param code
	 */
	public DeedtypePk(final int code)
	{
		this.code = code;
	}

	/** 
	 * Sets the value of codeNull
	 */
	public void setCodeNull(boolean codeNull)
	{
		this.codeNull = codeNull;
	}

	/** 
	 * Gets the value of codeNull
	 */
	public boolean isCodeNull()
	{
		return codeNull;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof DeedtypePk)) {
			return false;
		}
		
		final DeedtypePk _cast = (DeedtypePk) _other;
		if (code != _cast.code) {
			return false;
		}
		
		if (codeNull != _cast.codeNull) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + code;
		_hashCode = 29 * _hashCode + (codeNull ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "org.nic.epanjeeyan.dto.DeedtypePk: " );
		ret.append( "code=" + code );
		return ret.toString();
	}

}
