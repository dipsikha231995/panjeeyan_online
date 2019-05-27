<%-- 
    Document   : egrasPayment
    Created on : Feb 13, 2019, 12:27:14 PM
    Author     : JIT
--%>
<style>
    body{display:none !important;}
</style>
<div class="container">
<form id="frmenquiry" class="form-horizontal" method="POST" action="http://103.8.248.139/challan/views/frmgrnfordept.php" name="frmenquiry" enctype="multipart/form-data" >
    
    <input type="hidden" name="PAYMENT_TYPE" id="PAYMENT_TYPE" value="${PAYMENT_TYPE}"   />
    <input type="hidden" name="TREASURY_CODE" id="TREASURY_CODE" value="${TREASURY_CODE}"    />
    <input type="hidden" name="DEPT_CODE" id="DEPT_CODE" value="${DEPT_CODE}"   />
    <input type="hidden" name="REC_FIN_YEAR" id="DEPT_CODE" value="${REC_FIN_YEAR}"   />
    <input id="MAJOR_HEAD" name="MAJOR_HEAD" type="hidden"  class="form-control"  value="${MAJOR_HEAD}"  data-date-today-highlight="true"  data-validation="required"   />
    <input type="hidden" name="HOA1" id="HOA1" value="${HOA1}"     />
    <input type="hidden" name="AMOUNT1" id="AMOUNT1" value="${AMOUNT1}"    />
    <input type="hidden" name="OFFICE_CODE" id="OFFICE_CODE" value="${OFFICE_CODE}"    />
    <input type="hidden" name="PERIOD" id="PERIOD" value="${PERIOD}"    />
    <input type="hidden" name="FROM_DATE" id="FROM_DATE" value="${FROM_DATE}"    />
    <input type="hidden" name="TO_DATE" id="TO_DATE" value="${TO_DATE}"    />
    <input type="hidden" name="CHALLAN_AMOUNT" id="CHALLAN_AMOUNT" value="${CHALLAN_AMOUNT}"    />
    <input type="hidden" name="PARTY_NAME" id="PARTY_NAME" value="${PARTY_NAME}"    />
    <input type="hidden" name="TAX_ID" id="TAX_ID" value="${TAX_ID}"    />
    <input type="hidden" name="PAN_NO" id="PAN_NO" value="${PAN_NO}"    />
    <input type="hidden" name="ADDRESS1" id="ADDRESS1" value="${ADDRESS1}"   />
    <input type="hidden" name="ADDRESS2" id="ADDRESS2" value="${ADDRESS2}"   />
    <input type="hidden" name="ADDRESS3" id="ADDRESS3" value="${ADDRESS3}"    />
    <input type="hidden" name="PIN_NO" id="PIN_NO" value="${PIN_NO}"    />
    <input type="hidden" name="MOBILE_NO" id="MOBILE_NO" value="${MOBILE_NO}"    />
    <input type="hidden" name="SUB_SYSTEM" id="SUB_SYSTEM" value="${SUB_SYSTEM}"    />
    <input type="hidden" name="DEPARTMENT_ID" id="DEPARTMENT_ID" value="${DEPARTMENT_ID}"    />
    
</form>
</div>
    <script>
    $(document).ready(function(){
        $('body').hide();
        $('#frmenquiry').submit();
        
    });
    
</script>

