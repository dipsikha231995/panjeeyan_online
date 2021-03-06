/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.dto;

import org.nic.epanjeeyan.dao.*;
import org.nic.epanjeeyan.factory.*;
import org.nic.epanjeeyan.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class Marriagefee implements Serializable
{
	/** 
	 * This attribute maps to the column feeName in the marriagefee table.
	 */
	protected String feeName;

	/** 
	 * This attribute maps to the column feeValue in the marriagefee table.
	 */
	protected int feeValue;

	/**
	 * Method 'Marriagefee'
	 * 
	 */
	public Marriagefee()
	{
	}

	/**
	 * Method 'getFeeName'
	 * 
	 * @return String
	 */
	public String getFeeName()
	{
		return feeName;
	}

	/**
	 * Method 'setFeeName'
	 * 
	 * @param feeName
	 */
	public void setFeeName(String feeName)
	{
		this.feeName = feeName;
	}

	/**
	 * Method 'getFeeValue'
	 * 
	 * @return int
	 */
	public int getFeeValue()
	{
		return feeValue;
	}

	/**
	 * Method 'setFeeValue'
	 * 
	 * @param feeValue
	 */
	public void setFeeValue(int feeValue)
	{
		this.feeValue = feeValue;
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
		
		if (!(_other instanceof Marriagefee)) {
			return false;
		}
		
		final Marriagefee _cast = (Marriagefee) _other;
		if (feeName == null ? _cast.feeName != feeName : !feeName.equals( _cast.feeName )) {
			return false;
		}
		
		if (feeValue != _cast.feeValue) {
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
		if (feeName != null) {
			_hashCode = 29 * _hashCode + feeName.hashCode();
		}
		
		_hashCode = 29 * _hashCode + feeValue;
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
		ret.append( "org.nic.epanjeeyan.dto.Marriagefee: " );
		ret.append( "feeName=" + feeName );
		ret.append( ", feeValue=" + feeValue );
		return ret.toString();
	}

}
