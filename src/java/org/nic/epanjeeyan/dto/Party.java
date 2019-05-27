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

public class Party implements Serializable
{
	/** 
	 * This attribute maps to the column auto_id in the party table.
	 */
	protected Long autoId;

	/** 
	 * This attribute maps to the column slno in the party table.
	 */
	protected Integer slno;

	/** 
	 * This attribute maps to the column party_comcaseno in the party table.
	 */
	protected String partyComcaseno;

	/** 
	 * This attribute maps to the column noofp in the party table.
	 */
	protected Integer noofp;

	/** 
	 * This attribute maps to the column nameparty in the party table.
	 */
	protected String nameparty;

	/** 
	 * This attribute maps to the column noofpersons in the party table.
	 */
	protected Integer noofpersons;

	/** 
	 * This attribute maps to the column fname in the party table.
	 */
	protected String fname;

	/** 
	 * This attribute maps to the column address in the party table.
	 */
	protected String address;

	/** 
	 * This attribute maps to the column village in the party table.
	 */
	protected String village;

	/** 
	 * This attribute maps to the column poff in the party table.
	 */
	protected String poff;

	/** 
	 * This attribute maps to the column pstat in the party table.
	 */
	protected String pstat;

	/** 
	 * This attribute maps to the column district in the party table.
	 */
	protected String district;

	/** 
	 * This attribute maps to the column state in the party table.
	 */
	protected String state;

	/** 
	 * This attribute maps to the column partytype in the party table.
	 */
	protected String partytype;

	/** 
	 * This attribute maps to the column check_complete in the party table.
	 */
	protected Short checkComplete;

	/** 
	 * This attribute maps to the column district_code in the party table.
	 */
	protected String districtCode;

	/** 
	 * This attribute maps to the column sro_code in the party table.
	 */
	protected String sroCode;

	/** 
	 * This attribute maps to the column firstPartyComplete in the party table.
	 */
	protected Short firstPartyComplete;

	/** 
	 * This attribute maps to the column category in the party table.
	 */
	protected Integer category;

	/**
	 * Method 'Party'
	 * 
	 */
	public Party()
	{
	}

	/**
	 * Method 'getAutoId'
	 * 
	 * @return Long
	 */
	public Long getAutoId()
	{
		return autoId;
	}

	/**
	 * Method 'setAutoId'
	 * 
	 * @param autoId
	 */
	public void setAutoId(Long autoId)
	{
		this.autoId = autoId;
	}

	/**
	 * Method 'getSlno'
	 * 
	 * @return Integer
	 */
	public Integer getSlno()
	{
		return slno;
	}

	/**
	 * Method 'setSlno'
	 * 
	 * @param slno
	 */
	public void setSlno(Integer slno)
	{
		this.slno = slno;
	}

	/**
	 * Method 'getPartyComcaseno'
	 * 
	 * @return String
	 */
	public String getPartyComcaseno()
	{
		return partyComcaseno;
	}

	/**
	 * Method 'setPartyComcaseno'
	 * 
	 * @param partyComcaseno
	 */
	public void setPartyComcaseno(String partyComcaseno)
	{
		this.partyComcaseno = partyComcaseno;
	}

	/**
	 * Method 'getNoofp'
	 * 
	 * @return Integer
	 */
	public Integer getNoofp()
	{
		return noofp;
	}

	/**
	 * Method 'setNoofp'
	 * 
	 * @param noofp
	 */
	public void setNoofp(Integer noofp)
	{
		this.noofp = noofp;
	}

	/**
	 * Method 'getNameparty'
	 * 
	 * @return String
	 */
	public String getNameparty()
	{
		return nameparty;
	}

	/**
	 * Method 'setNameparty'
	 * 
	 * @param nameparty
	 */
	public void setNameparty(String nameparty)
	{
		this.nameparty = nameparty;
	}

	/**
	 * Method 'getNoofpersons'
	 * 
	 * @return Integer
	 */
	public Integer getNoofpersons()
	{
		return noofpersons;
	}

	/**
	 * Method 'setNoofpersons'
	 * 
	 * @param noofpersons
	 */
	public void setNoofpersons(Integer noofpersons)
	{
		this.noofpersons = noofpersons;
	}

	/**
	 * Method 'getFname'
	 * 
	 * @return String
	 */
	public String getFname()
	{
		return fname;
	}

	/**
	 * Method 'setFname'
	 * 
	 * @param fname
	 */
	public void setFname(String fname)
	{
		this.fname = fname;
	}

	/**
	 * Method 'getAddress'
	 * 
	 * @return String
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Method 'setAddress'
	 * 
	 * @param address
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * Method 'getVillage'
	 * 
	 * @return String
	 */
	public String getVillage()
	{
		return village;
	}

	/**
	 * Method 'setVillage'
	 * 
	 * @param village
	 */
	public void setVillage(String village)
	{
		this.village = village;
	}

	/**
	 * Method 'getPoff'
	 * 
	 * @return String
	 */
	public String getPoff()
	{
		return poff;
	}

	/**
	 * Method 'setPoff'
	 * 
	 * @param poff
	 */
	public void setPoff(String poff)
	{
		this.poff = poff;
	}

	/**
	 * Method 'getPstat'
	 * 
	 * @return String
	 */
	public String getPstat()
	{
		return pstat;
	}

	/**
	 * Method 'setPstat'
	 * 
	 * @param pstat
	 */
	public void setPstat(String pstat)
	{
		this.pstat = pstat;
	}

	/**
	 * Method 'getDistrict'
	 * 
	 * @return String
	 */
	public String getDistrict()
	{
		return district;
	}

	/**
	 * Method 'setDistrict'
	 * 
	 * @param district
	 */
	public void setDistrict(String district)
	{
		this.district = district;
	}

	/**
	 * Method 'getState'
	 * 
	 * @return String
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * Method 'setState'
	 * 
	 * @param state
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * Method 'getPartytype'
	 * 
	 * @return String
	 */
	public String getPartytype()
	{
		return partytype;
	}

	/**
	 * Method 'setPartytype'
	 * 
	 * @param partytype
	 */
	public void setPartytype(String partytype)
	{
		this.partytype = partytype;
	}

	/**
	 * Method 'getCheckComplete'
	 * 
	 * @return Short
	 */
	public Short getCheckComplete()
	{
		return checkComplete;
	}

	/**
	 * Method 'setCheckComplete'
	 * 
	 * @param checkComplete
	 */
	public void setCheckComplete(Short checkComplete)
	{
		this.checkComplete = checkComplete;
	}

	/**
	 * Method 'getDistrictCode'
	 * 
	 * @return String
	 */
	public String getDistrictCode()
	{
		return districtCode;
	}

	/**
	 * Method 'setDistrictCode'
	 * 
	 * @param districtCode
	 */
	public void setDistrictCode(String districtCode)
	{
		this.districtCode = districtCode;
	}

	/**
	 * Method 'getSroCode'
	 * 
	 * @return String
	 */
	public String getSroCode()
	{
		return sroCode;
	}

	/**
	 * Method 'setSroCode'
	 * 
	 * @param sroCode
	 */
	public void setSroCode(String sroCode)
	{
		this.sroCode = sroCode;
	}

	/**
	 * Method 'getFirstPartyComplete'
	 * 
	 * @return Short
	 */
	public Short getFirstPartyComplete()
	{
		return firstPartyComplete;
	}

	/**
	 * Method 'setFirstPartyComplete'
	 * 
	 * @param firstPartyComplete
	 */
	public void setFirstPartyComplete(Short firstPartyComplete)
	{
		this.firstPartyComplete = firstPartyComplete;
	}

	/**
	 * Method 'getCategory'
	 * 
	 * @return Integer
	 */
	public Integer getCategory()
	{
		return category;
	}

	/**
	 * Method 'setCategory'
	 * 
	 * @param category
	 */
	public void setCategory(Integer category)
	{
		this.category = category;
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
		
		if (!(_other instanceof Party)) {
			return false;
		}
		
		final Party _cast = (Party) _other;
		if (autoId == null ? _cast.autoId != autoId : !autoId.equals( _cast.autoId )) {
			return false;
		}
		
		if (slno == null ? _cast.slno != slno : !slno.equals( _cast.slno )) {
			return false;
		}
		
		if (partyComcaseno == null ? _cast.partyComcaseno != partyComcaseno : !partyComcaseno.equals( _cast.partyComcaseno )) {
			return false;
		}
		
		if (noofp == null ? _cast.noofp != noofp : !noofp.equals( _cast.noofp )) {
			return false;
		}
		
		if (nameparty == null ? _cast.nameparty != nameparty : !nameparty.equals( _cast.nameparty )) {
			return false;
		}
		
		if (noofpersons == null ? _cast.noofpersons != noofpersons : !noofpersons.equals( _cast.noofpersons )) {
			return false;
		}
		
		if (fname == null ? _cast.fname != fname : !fname.equals( _cast.fname )) {
			return false;
		}
		
		if (address == null ? _cast.address != address : !address.equals( _cast.address )) {
			return false;
		}
		
		if (village == null ? _cast.village != village : !village.equals( _cast.village )) {
			return false;
		}
		
		if (poff == null ? _cast.poff != poff : !poff.equals( _cast.poff )) {
			return false;
		}
		
		if (pstat == null ? _cast.pstat != pstat : !pstat.equals( _cast.pstat )) {
			return false;
		}
		
		if (district == null ? _cast.district != district : !district.equals( _cast.district )) {
			return false;
		}
		
		if (state == null ? _cast.state != state : !state.equals( _cast.state )) {
			return false;
		}
		
		if (partytype == null ? _cast.partytype != partytype : !partytype.equals( _cast.partytype )) {
			return false;
		}
		
		if (checkComplete == null ? _cast.checkComplete != checkComplete : !checkComplete.equals( _cast.checkComplete )) {
			return false;
		}
		
		if (districtCode == null ? _cast.districtCode != districtCode : !districtCode.equals( _cast.districtCode )) {
			return false;
		}
		
		if (sroCode == null ? _cast.sroCode != sroCode : !sroCode.equals( _cast.sroCode )) {
			return false;
		}
		
		if (firstPartyComplete == null ? _cast.firstPartyComplete != firstPartyComplete : !firstPartyComplete.equals( _cast.firstPartyComplete )) {
			return false;
		}
		
		if (category == null ? _cast.category != category : !category.equals( _cast.category )) {
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
		if (autoId != null) {
			_hashCode = 29 * _hashCode + autoId.hashCode();
		}
		
		if (slno != null) {
			_hashCode = 29 * _hashCode + slno.hashCode();
		}
		
		if (partyComcaseno != null) {
			_hashCode = 29 * _hashCode + partyComcaseno.hashCode();
		}
		
		if (noofp != null) {
			_hashCode = 29 * _hashCode + noofp.hashCode();
		}
		
		if (nameparty != null) {
			_hashCode = 29 * _hashCode + nameparty.hashCode();
		}
		
		if (noofpersons != null) {
			_hashCode = 29 * _hashCode + noofpersons.hashCode();
		}
		
		if (fname != null) {
			_hashCode = 29 * _hashCode + fname.hashCode();
		}
		
		if (address != null) {
			_hashCode = 29 * _hashCode + address.hashCode();
		}
		
		if (village != null) {
			_hashCode = 29 * _hashCode + village.hashCode();
		}
		
		if (poff != null) {
			_hashCode = 29 * _hashCode + poff.hashCode();
		}
		
		if (pstat != null) {
			_hashCode = 29 * _hashCode + pstat.hashCode();
		}
		
		if (district != null) {
			_hashCode = 29 * _hashCode + district.hashCode();
		}
		
		if (state != null) {
			_hashCode = 29 * _hashCode + state.hashCode();
		}
		
		if (partytype != null) {
			_hashCode = 29 * _hashCode + partytype.hashCode();
		}
		
		if (checkComplete != null) {
			_hashCode = 29 * _hashCode + checkComplete.hashCode();
		}
		
		if (districtCode != null) {
			_hashCode = 29 * _hashCode + districtCode.hashCode();
		}
		
		if (sroCode != null) {
			_hashCode = 29 * _hashCode + sroCode.hashCode();
		}
		
		if (firstPartyComplete != null) {
			_hashCode = 29 * _hashCode + firstPartyComplete.hashCode();
		}
		
		if (category != null) {
			_hashCode = 29 * _hashCode + category.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return PartyPk
	 */
	public PartyPk createPk()
	{
		return new PartyPk(slno, partyComcaseno, partytype);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "org.nic.epanjeeyan.dto.Party: " );
		ret.append( "autoId=" + autoId );
		ret.append( ", slno=" + slno );
		ret.append( ", partyComcaseno=" + partyComcaseno );
		ret.append( ", noofp=" + noofp );
		ret.append( ", nameparty=" + nameparty );
		ret.append( ", noofpersons=" + noofpersons );
		ret.append( ", fname=" + fname );
		ret.append( ", address=" + address );
		ret.append( ", village=" + village );
		ret.append( ", poff=" + poff );
		ret.append( ", pstat=" + pstat );
		ret.append( ", district=" + district );
		ret.append( ", state=" + state );
		ret.append( ", partytype=" + partytype );
		ret.append( ", checkComplete=" + checkComplete );
		ret.append( ", districtCode=" + districtCode );
		ret.append( ", sroCode=" + sroCode );
		ret.append( ", firstPartyComplete=" + firstPartyComplete );
		ret.append( ", category=" + category );
		return ret.toString();
	}

}
