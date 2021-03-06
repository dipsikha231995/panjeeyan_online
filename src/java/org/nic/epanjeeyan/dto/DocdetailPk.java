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
 * This class represents the primary key of the docdetail table.
 */
public class DocdetailPk implements Serializable
{
	protected int caseslno;

	protected String comcaseno;

	/** 
	 * This attribute represents whether the primitive attribute caseslno is null.
	 */
	protected boolean caseslnoNull;

	/** 
	 * Sets the value of caseslno
	 */
	public void setCaseslno(int caseslno)
	{
		this.caseslno = caseslno;
	}

	/** 
	 * Gets the value of caseslno
	 */
	public int getCaseslno()
	{
		return caseslno;
	}

	/** 
	 * Sets the value of comcaseno
	 */
	public void setComcaseno(String comcaseno)
	{
		this.comcaseno = comcaseno;
	}

	/** 
	 * Gets the value of comcaseno
	 */
	public String getComcaseno()
	{
		return comcaseno;
	}

	/**
	 * Method 'DocdetailPk'
	 * 
	 */
	public DocdetailPk()
	{
	}

	/**
	 * Method 'DocdetailPk'
	 * 
	 * @param caseslno
	 * @param comcaseno
	 */
	public DocdetailPk(final int caseslno, final String comcaseno)
	{
		this.caseslno = caseslno;
		this.comcaseno = comcaseno;
	}

	/** 
	 * Sets the value of caseslnoNull
	 */
	public void setCaseslnoNull(boolean caseslnoNull)
	{
		this.caseslnoNull = caseslnoNull;
	}

	/** 
	 * Gets the value of caseslnoNull
	 */
	public boolean isCaseslnoNull()
	{
		return caseslnoNull;
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
		
		if (!(_other instanceof DocdetailPk)) {
			return false;
		}
		
		final DocdetailPk _cast = (DocdetailPk) _other;
		if (caseslno != _cast.caseslno) {
			return false;
		}
		
		if (comcaseno == null ? _cast.comcaseno != comcaseno : !comcaseno.equals( _cast.comcaseno )) {
			return false;
		}
		
		if (caseslnoNull != _cast.caseslnoNull) {
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
		_hashCode = 29 * _hashCode + caseslno;
		if (comcaseno != null) {
			_hashCode = 29 * _hashCode + comcaseno.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (caseslnoNull ? 1 : 0);
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
		ret.append( "org.nic.epanjeeyan.dto.DocdetailPk: " );
		ret.append( "caseslno=" + caseslno );
		ret.append( ", comcaseno=" + comcaseno );
		return ret.toString();
	}

}
