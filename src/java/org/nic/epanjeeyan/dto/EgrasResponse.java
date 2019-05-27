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

public class EgrasResponse implements Serializable
{
	/** 
	 * This attribute maps to the column id in the egras_response table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column departmentId in the egras_response table.
	 */
	protected String departmentId;

	/** 
	 * This attribute maps to the column officeCode in the egras_response table.
	 */
	protected String officeCode;

	/** 
	 * This attribute maps to the column grnNo in the egras_response table.
	 */
	protected String grnNo;

	/** 
	 * This attribute maps to the column requestParameters in the egras_response table.
	 */
	protected String requestParameters;

	/** 
	 * This attribute maps to the column responseParameters in the egras_response table.
	 */
	protected String responseParameters;

	/** 
	 * This attribute maps to the column mobileNo in the egras_response table.
	 */
	protected int mobileNo;

	/** 
	 * This attribute maps to the column amount in the egras_response table.
	 */
	protected int amount;

	/** 
	 * This attribute maps to the column cin in the egras_response table.
	 */
	protected String cin;

	/** 
	 * This attribute maps to the column year in the egras_response table.
	 */
	protected int year;

	/**
	 * Method 'EgrasResponse'
	 * 
	 */
	public EgrasResponse()
	{
	}

	/**
	 * Method 'getId'
	 * 
	 * @return int
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Method 'getDepartmentId'
	 * 
	 * @return String
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * Method 'setDepartmentId'
	 * 
	 * @param departmentId
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}

	/**
	 * Method 'getOfficeCode'
	 * 
	 * @return String
	 */
	public String getOfficeCode()
	{
		return officeCode;
	}

	/**
	 * Method 'setOfficeCode'
	 * 
	 * @param officeCode
	 */
	public void setOfficeCode(String officeCode)
	{
		this.officeCode = officeCode;
	}

	/**
	 * Method 'getGrnNo'
	 * 
	 * @return String
	 */
	public String getGrnNo()
	{
		return grnNo;
	}

	/**
	 * Method 'setGrnNo'
	 * 
	 * @param grnNo
	 */
	public void setGrnNo(String grnNo)
	{
		this.grnNo = grnNo;
	}

	/**
	 * Method 'getRequestParameters'
	 * 
	 * @return String
	 */
	public String getRequestParameters()
	{
		return requestParameters;
	}

	/**
	 * Method 'setRequestParameters'
	 * 
	 * @param requestParameters
	 */
	public void setRequestParameters(String requestParameters)
	{
		this.requestParameters = requestParameters;
	}

	/**
	 * Method 'getResponseParameters'
	 * 
	 * @return String
	 */
	public String getResponseParameters()
	{
		return responseParameters;
	}

	/**
	 * Method 'setResponseParameters'
	 * 
	 * @param responseParameters
	 */
	public void setResponseParameters(String responseParameters)
	{
		this.responseParameters = responseParameters;
	}

	/**
	 * Method 'getMobileNo'
	 * 
	 * @return int
	 */
	public int getMobileNo()
	{
		return mobileNo;
	}

	/**
	 * Method 'setMobileNo'
	 * 
	 * @param mobileNo
	 */
	public void setMobileNo(int mobileNo)
	{
		this.mobileNo = mobileNo;
	}

	/**
	 * Method 'getAmount'
	 * 
	 * @return int
	 */
	public int getAmount()
	{
		return amount;
	}

	/**
	 * Method 'setAmount'
	 * 
	 * @param amount
	 */
	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	/**
	 * Method 'getCin'
	 * 
	 * @return String
	 */
	public String getCin()
	{
		return cin;
	}

	/**
	 * Method 'setCin'
	 * 
	 * @param cin
	 */
	public void setCin(String cin)
	{
		this.cin = cin;
	}

	/**
	 * Method 'getYear'
	 * 
	 * @return int
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * Method 'setYear'
	 * 
	 * @param year
	 */
	public void setYear(int year)
	{
		this.year = year;
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
		
		if (!(_other instanceof EgrasResponse)) {
			return false;
		}
		
		final EgrasResponse _cast = (EgrasResponse) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (departmentId == null ? _cast.departmentId != departmentId : !departmentId.equals( _cast.departmentId )) {
			return false;
		}
		
		if (officeCode == null ? _cast.officeCode != officeCode : !officeCode.equals( _cast.officeCode )) {
			return false;
		}
		
		if (grnNo == null ? _cast.grnNo != grnNo : !grnNo.equals( _cast.grnNo )) {
			return false;
		}
		
		if (requestParameters == null ? _cast.requestParameters != requestParameters : !requestParameters.equals( _cast.requestParameters )) {
			return false;
		}
		
		if (responseParameters == null ? _cast.responseParameters != responseParameters : !responseParameters.equals( _cast.responseParameters )) {
			return false;
		}
		
		if (mobileNo != _cast.mobileNo) {
			return false;
		}
		
		if (amount != _cast.amount) {
			return false;
		}
		
		if (cin == null ? _cast.cin != cin : !cin.equals( _cast.cin )) {
			return false;
		}
		
		if (year != _cast.year) {
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
		_hashCode = 29 * _hashCode + id;
		if (departmentId != null) {
			_hashCode = 29 * _hashCode + departmentId.hashCode();
		}
		
		if (officeCode != null) {
			_hashCode = 29 * _hashCode + officeCode.hashCode();
		}
		
		if (grnNo != null) {
			_hashCode = 29 * _hashCode + grnNo.hashCode();
		}
		
		if (requestParameters != null) {
			_hashCode = 29 * _hashCode + requestParameters.hashCode();
		}
		
		if (responseParameters != null) {
			_hashCode = 29 * _hashCode + responseParameters.hashCode();
		}
		
		_hashCode = 29 * _hashCode + mobileNo;
		_hashCode = 29 * _hashCode + amount;
		if (cin != null) {
			_hashCode = 29 * _hashCode + cin.hashCode();
		}
		
		_hashCode = 29 * _hashCode + year;
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return EgrasResponsePk
	 */
	public EgrasResponsePk createPk()
	{
		return new EgrasResponsePk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "org.nic.epanjeeyan.dto.EgrasResponse: " );
		ret.append( "id=" + id );
		ret.append( ", departmentId=" + departmentId );
		ret.append( ", officeCode=" + officeCode );
		ret.append( ", grnNo=" + grnNo );
		ret.append( ", requestParameters=" + requestParameters );
		ret.append( ", responseParameters=" + responseParameters );
		ret.append( ", mobileNo=" + mobileNo );
		ret.append( ", amount=" + amount );
		ret.append( ", cin=" + cin );
		ret.append( ", year=" + year );
		return ret.toString();
	}

}