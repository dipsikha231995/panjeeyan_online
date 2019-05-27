/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.dao;

import java.util.Date;
import org.nic.epanjeeyan.dto.*;
import org.nic.epanjeeyan.exceptions.*;

public interface DocdetailDao
{
	/** 
	 * Inserts a new row in the docdetail table.
	 */
	public DocdetailPk insert(Docdetail dto) throws DocdetailDaoException;

	/** 
	 * Updates a single row in the docdetail table.
	 */
	public void update(DocdetailPk pk, Docdetail dto) throws DocdetailDaoException;

	/** 
	 * Deletes a single row in the docdetail table.
	 */
	public void delete(DocdetailPk pk) throws DocdetailDaoException;

	/** 
	 * Returns the rows from the docdetail table that matches the specified primary-key value.
	 */
	public Docdetail findByPrimaryKey(DocdetailPk pk) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'caseslno = :caseslno AND comcaseno = :comcaseno'.
	 */
	public Docdetail findByPrimaryKey(int caseslno, String comcaseno) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria ''.
	 */
	public Docdetail[] findAll() throws DocdetailDaoException;
        public Docdetail[] pageAll(Integer offset,Integer num) throws DocdetailDaoException;
	/** 
	 * Returns all rows from the docdetail table that match the criteria 'slno = :slno'.
	 */
	public Docdetail[] findWhereSlnoEquals(int slno) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'caseslno = :caseslno'.
	 */
	public Docdetail[] findWhereCaseslnoEquals(int caseslno) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'volslno = :volslno'.
	 */
	public Docdetail[] findWhereVolslnoEquals(int volslno) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'fcaseno = :fcaseno'.
	 */
	public Docdetail[] findWhereFcasenoEquals(String fcaseno) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'casedate = :casedate'.
	 */
	public Docdetail[] findWhereCasedateEquals(Date casedate) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'comcaseno = :comcaseno'.
	 */
	public Docdetail[] findWhereComcasenoEquals(String comcaseno) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'category = :category'.
	 */
	public Docdetail[] findWhereCategoryEquals(int category) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'conamount = :conamount'.
	 */
	public Docdetail[] findWhereConamountEquals(int conamount) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'area = :area'.
	 */
	public Docdetail[] findWhereAreaEquals(String area) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'rfee = :rfee'.
	 */
	public Docdetail[] findWhereRfeeEquals(int rfee) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'afee = :afee'.
	 */
	public Docdetail[] findWhereAfeeEquals(int afee) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'recno = :recno'.
	 */
	public Docdetail[] findWhereRecnoEquals(String recno) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'recdate = :recdate'.
	 */
	public Docdetail[] findWhereRecdateEquals(Date recdate) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'arecno = :arecno'.
	 */
	public Docdetail[] findWhereArecnoEquals(String arecno) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'arecdt = :arecdt'.
	 */
	public Docdetail[] findWhereArecdtEquals(Date arecdt) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'nocno = :nocno'.
	 */
	public Docdetail[] findWhereNocnoEquals(String nocno) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'nocdate = :nocdate'.
	 */
	public Docdetail[] findWhereNocdateEquals(Date nocdate) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'tdanoc = :tdanoc'.
	 */
	public Docdetail[] findWhereTdanocEquals(String tdanoc) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'tdanocdate = :tdanocdate'.
	 */
	public Docdetail[] findWhereTdanocdateEquals(Date tdanocdate) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no50000 = :no50000'.
	 */
	public Docdetail[] findWhereNo50000Equals(int no50000) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no25000 = :no25000'.
	 */
	public Docdetail[] findWhereNo25000Equals(int no25000) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no20000 = :no20000'.
	 */
	public Docdetail[] findWhereNo20000Equals(int no20000) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no15000 = :no15000'.
	 */
	public Docdetail[] findWhereNo15000Equals(int no15000) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no10000 = :no10000'.
	 */
	public Docdetail[] findWhereNo10000Equals(int no10000) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no5000 = :no5000'.
	 */
	public Docdetail[] findWhereNo5000Equals(int no5000) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no1000 = :no1000'.
	 */
	public Docdetail[] findWhereNo1000Equals(int no1000) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no500 = :no500'.
	 */
	public Docdetail[] findWhereNo500Equals(int no500) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no200 = :no200'.
	 */
	public Docdetail[] findWhereNo200Equals(int no200) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no100 = :no100'.
	 */
	public Docdetail[] findWhereNo100Equals(int no100) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no50 = :no50'.
	 */
	public Docdetail[] findWhereNo50Equals(int no50) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no20 = :no20'.
	 */
	public Docdetail[] findWhereNo20Equals(int no20) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no10 = :no10'.
	 */
	public Docdetail[] findWhereNo10Equals(int no10) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no5 = :no5'.
	 */
	public Docdetail[] findWhereNo5Equals(int no5) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no2 = :no2'.
	 */
	public Docdetail[] findWhereNo2Equals(int no2) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'no1 = :no1'.
	 */
	public Docdetail[] findWhereNo1Equals(int no1) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'folname = :folname'.
	 */
	public Docdetail[] findWhereFolnameEquals(String folname) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'nopage = :nopage'.
	 */
	public Docdetail[] findWhereNopageEquals(int nopage) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'pridate = :pridate'.
	 */
	public Docdetail[] findWherePridateEquals(Date pridate) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'scdate = :scdate'.
	 */
	public Docdetail[] findWhereScdateEquals(Date scdate) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'usernm = :usernm'.
	 */
	public Docdetail[] findWhereUsernmEquals(String usernm) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'volno = :volno'.
	 */
	public Docdetail[] findWhereVolnoEquals(String volno) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'docsub = :docsub'.
	 */
	public Docdetail[] findWhereDocsubEquals(String docsub) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'rocom = :rocom'.
	 */
	public Docdetail[] findWhereRocomEquals(String rocom) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'dtadmin = :dtadmin'.
	 */
	public Docdetail[] findWhereDtadminEquals(Date dtadmin) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'dtcomple = :dtcomple'.
	 */
	public Docdetail[] findWhereDtcompleEquals(Date dtcomple) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'actchargable = :actchargable'.
	 */
	public Docdetail[] findWhereActchargableEquals(String actchargable) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'nmapplicant = :nmapplicant'.
	 */
	public Docdetail[] findWhereNmapplicantEquals(String nmapplicant) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'iname = :iname'.
	 */
	public Docdetail[] findWhereInameEquals(String iname) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'stampfee = :stampfee'.
	 */
	public Docdetail[] findWhereStampfeeEquals(int stampfee) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'catclassi = :catclassi'.
	 */
	public Docdetail[] findWhereCatclassiEquals(int catclassi) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'gbfee = :gbfee'.
	 */
	public Docdetail[] findWhereGbfeeEquals(int gbfee) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'kfee = :kfee'.
	 */
	public Docdetail[] findWhereKfeeEquals(int kfee) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'ofee = :ofee'.
	 */
	public Docdetail[] findWhereOfeeEquals(int ofee) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'ccfee = :ccfee'.
	 */
	public Docdetail[] findWhereCcfeeEquals(int ccfee) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'mafee = :mafee'.
	 */
	public Docdetail[] findWhereMafeeEquals(int mafee) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'ffee = :ffee'.
	 */
	public Docdetail[] findWhereFfeeEquals(int ffee) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'dctype = :dctype'.
	 */
	public Docdetail[] findWhereDctypeEquals(String dctype) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'check_complete = :checkComplete'.
	 */
	public Docdetail[] findWhereCheckCompleteEquals(int checkComplete) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'witness_complete = :witnessComplete'.
	 */
	public Docdetail[] findWhereWitnessCompleteEquals(int witnessComplete) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'party_complete = :partyComplete'.
	 */
	public Docdetail[] findWherePartyCompleteEquals(int partyComplete) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'land_complete = :landComplete'.
	 */
	public Docdetail[] findWhereLandCompleteEquals(int landComplete) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'land_related = :landRelated'.
	 */
	public Docdetail[] findWhereLandRelatedEquals(int landRelated) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'district_code = :districtCode'.
	 */
	public Docdetail[] findWhereDistrictCodeEquals(String districtCode) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'sro_code = :sroCode'.
	 */
	public Docdetail[] findWhereSroCodeEquals(String sroCode) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'reg_fee = :regFee'.
	 */
	public Docdetail[] findWhereRegFeeEquals(int regFee) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'other_fee = :otherFee'.
	 */
	public Docdetail[] findWhereOtherFeeEquals(int otherFee) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'reg_reciept_no = :regRecieptNo'.
	 */
	public Docdetail[] findWhereRegRecieptNoEquals(String regRecieptNo) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'reg_reciept_date = :regRecieptDate'.
	 */
	public Docdetail[] findWhereRegRecieptDateEquals(Date regRecieptDate) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'sub_deed_type = :subDeedType'.
	 */
	public Docdetail[] findWhereSubDeedTypeEquals(String subDeedType) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'area_type = :areaType'.
	 */
	public Docdetail[] findWhereAreaTypeEquals(String areaType) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'bookindex = :bookindex'.
	 */
	public Docdetail[] findWhereBookindexEquals(String bookindex) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'ro_complete = :roComplete'.
	 */
	public Docdetail[] findWhereRoCompleteEquals(int roComplete) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'scn_cmplt = :scnCmplt'.
	 */
	public Docdetail[] findWhereScnCmpltEquals(int scnCmplt) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'ro_authentication = :roAuthentication'.
	 */
	public Docdetail[] findWhereRoAuthenticationEquals(int roAuthentication) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'rejection = :rejection'.
	 */
	public Docdetail[] findWhereRejectionEquals(int rejection) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'doc_scan_complete = :docScanComplete'.
	 */
	public Docdetail[] findWhereDocScanCompleteEquals(int docScanComplete) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'fngr_scan_cmplt = :fngrScanCmplt'.
	 */
	public Docdetail[] findWhereFngrScanCmpltEquals(int fngrScanCmplt) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'img_scan_cmplt = :imgScanCmplt'.
	 */
	public Docdetail[] findWhereImgScanCmpltEquals(int imgScanCmplt) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'whether_old_doc = :whetherOldDoc'.
	 */
	public Docdetail[] findWhereWhetherOldDocEquals(int whetherOldDoc) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'type_of_reg = :typeOfReg'.
	 */
	public Docdetail[] findWhereTypeOfRegEquals(String typeOfReg) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'doc_pending = :docPending'.
	 */
	public Docdetail[] findWhereDocPendingEquals(int docPending) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'issue_date = :issueDate'.
	 */
	public Docdetail[] findWhereIssueDateEquals(Date issueDate) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'srnt_pen = :srntPen'.
	 */
	public Docdetail[] findWhereSrntPenEquals(int srntPen) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the criteria 'impound_doc = :impoundDoc'.
	 */
	public Docdetail[] findWhereImpoundDocEquals(int impoundDoc) throws DocdetailDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the docdetail table that match the specified arbitrary SQL statement
	 */
	public Docdetail[] findByDynamicSelect(String sql, Object[] sqlParams) throws DocdetailDaoException;

	/** 
	 * Returns all rows from the docdetail table that match the specified arbitrary SQL statement
	 */
	public Docdetail[] findByDynamicWhere(String sql, Object[] sqlParams) throws DocdetailDaoException;

}
