<%-- 
    Document   : get_Cin
    Created on : Feb 26, 2019, 1:05:10 PM
    Author     : JIT
--%>

<form id="frmenquiry" action="http://103.8.248.139/challan/models/frmgetgrn.php" method="POST" class="form-horizontal">
            <input type="hidden" name="ACTION_CODE" id="ACTION_CODE" value="GETCIN" />
            <input type="hidden" name="SUB_SYSTEM" id="SUB_SYSTEM" value="LRC-EMOJNI"/>
            <input type="hidden" name="OFFICE_CODE" id="OFFICE_CODE" value="${OFFICE_CODE}"/>
            <input type="hidden" name="DEPARTMENT_ID" id="DEPARTMENT_ID" value="${DEPARTMENT_ID}" readonly="readonly" class="form-control"  />
            <input type="hidden" name="AMOUNT" id="AMOUNT" value="${AMOUNT}" readonly="readonly" class="form-control"  />
</form>
<script>
    $(document).ready(function(){
        $('body').hide();
        $('#frmenquiry').submit();
        
    });
    
</script>
