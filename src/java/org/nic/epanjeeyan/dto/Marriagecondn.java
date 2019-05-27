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

public class Marriagecondn implements Serializable
{
	/** 
	 * This attribute maps to the column marriageCondn in the marriagecondn table.
	 */
	protected String marriageCondn;

	/**
	 * Method 'Marriagecondn'
	 * 
	 */
	public Marriagecondn()
	{
	}

	/**
	 * Method 'getMarriageCondn'
	 * 
	 * @return String
	 */
	public String getMarriageCondn()
	{
		return marriageCondn;
	}

	/**
	 * Method 'setMarriageCondn'
	 * 
	 * @param marriageCondn
	 */
	public void setMarriageCondn(String marriageCondn)
	{
		this.marriageCondn = marriageCondn;
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
		
		if (!(_other instanceof Marriagecondn)) {
			return false;
		}
		
		final Marriagecondn _cast = (Marriagecondn) _other;
		if (marriageCondn == null ? _cast.marriageCondn != marriageCondn : !marriageCondn.equals( _cast.marriageCondn )) {
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
		if (marriageCondn != null) {
			_hashCode = 29 * _hashCode + marriageCondn.hashCode();
		}
		
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
		ret.append( "org.nic.epanjeeyan.dto.Marriagecondn: " );
		ret.append( "marriageCondn=" + marriageCondn );
		return ret.toString();
	}

}
