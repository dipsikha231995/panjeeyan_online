<script type="text/javascript">
    $(document).ready(function () {
        if ($("#Deedtype").val() !== -1) {
            var code = $("#Deedtype").val();
            var subdeed = $("#Subdeedtype").val();
            $.post("http://" + location.host + "/panjeeyanonline/subdeedbydeed", {id: code, subdeed: subdeed}, function (values) {
                $("#Subdeedtype").html(values);  
            });
            $.post("http://" + location.host + "/panjeeyanonline/getdeed_json", {id: code, subdeed: subdeed}, function (values) {
                //$(".json").html(values);  
            });
        }
        $("#Deedtype").change(function () {
            var code = $("#Deedtype").val();
            $.post("http://" + location.host + "/panjeeyanonline/getsubdeed", {id: code}, function (values) {
                if(values === "error") {
                    alert("Invalid values");
                }
                else {
                  $("#Subdeedtype").html(values);  
                }
                
            });
            $.post("http://" + location.host + "/panjeeyanonline/getsubdeed_json", {id: code}, function (values) {
                if(values === "error") {
                    alert("Invalid values");
                }
                else {
                  //$(".json").html(values);  
                }
                
            });
        });
    });
</script>
<a style="padding: 0px 6px;" href="index.jsp"><span class="glyphicon glyphicon-backward"></span> Go Back</a>

<div class="panel panel-info" style="margin-top: 10px;">
    <div class="panel-heading">
        <h2 class="panel-title">Form for Assessment of Stamp Duty and Registration Fee</h2>
    </div>
    <form class="form-horizontal"method="POST" action="enquiry_api_demo" name="frmenquiry"  >
        <div class="panel-body">

            <div class="form-group">
                ${appointment_failure}
            </div>
            <c:if test="${errormsg ne null}">
                <div class="alert-danger" style="margin: 10px 0;padding: 10px;">
                    <c:forEach var="error" items="${errormsg}">
                        <strong></strong> ${error}<br/>
                    </c:forEach>
                </div>
            </c:if>
            <p class="json"></p>
            
            <div class="form-group">
                <label class="col-sm-3 control-label" for="DeedType">Deed Category  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <select id="Deedtype" name="Deedtype" class="form-control" data-validation="required" data-validation-error-msg="Select Deed Type">
                        <option value="">Click here to Select</option>
                        <c:forEach var="Deedtype" items="${Deedtypes}" >
                            <option  value="${Deedtype.code}" <c:if test="${Deedtype.code == deedtype}"> selected="selected" </c:if>>${Deedtype.section}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="Subdeedtype">Deed Sub-category   <span class="glyphicon glyphicon-star"></span> </label>
                <div class="col-sm-4">
                    <select id="Subdeedtype" name="Subdeedtype" class="form-control" data-validation="required" data-validation-error-msg="Select Deed Type">
                        <option value="">Click here to Select</option>
                        <c:forEach var="subDeed" items="${Subdeedtypes}" >
                            <option  value="${subDeed.subDeedType}" <c:if test="${subDeed.subDeedType == subdeedtype}"> selected="selected" </c:if>>${subDeed.subDeedType}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="captcha"> Security Question <span class="glyphicon glyphicon-star"></span> &nbsp;&nbsp;${ran1} + ${ran2} = </label>
                <div class="col-sm-1">
                    <input id="captcha" name="captcha" type="text" placeholder="" class="form-control" data-validation="number" data-validation-error-msg="Invalid answer" >
                </div>
                <div class="captcha-text"> << Specify correct result of addition in the given box</div>
            </div>

            <hr />
            <h5 style="color: #0099ff;font-weight:bold;text-decoration: underline"> Below information is necessary only in case of deeds related to land/flat (e.g sale or gift of land/flat etc.)</h5> 

            <div class="form-group">
                <label class="col-sm-3 control-label" for="ConsiderationAmt">Consideration Amount (In Rupees)  </label>
                <div class="col-sm-4">
                    <input id="ConsiderationAmt" name="ConsiderationAmt" type="text" placeholder="" class="form-control" value="${conamount == null ? 0 : conamount}" data-validation="number" data-validation-error-msg="Numeric values only">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="Gender">Purchaser with </label>
                <div class="col-sm-9">
                    <label class="radio-inline"><input type="radio" name="Gender" value="MF" ${gender == 'MF' ? 'checked' : ''}>Both Female & Male Members</label>
                    <label class="radio-inline"><input type="radio" name="Gender" value="F" ${gender == 'F' ? 'checked' : ''}>Only Female Member/Members</label>
                    <label class="radio-inline"><input type="radio" name="Gender" value="M" ${gender == 'M' ? 'checked' : ''}>Only Male Member/Members</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="UrbanRural">Land/Flat is located in</label>
                <div class="col-sm-9">
                    <label class="radio-inline"><input type="radio" name="UrbanRural" value="UG" <c:if test="${areatype == 'UG'}"> checked </c:if>>Urban(Municipality Corporation) Area</label>
                    <label class="radio-inline"><input type="radio" name="UrbanRural" value="UM" <c:if test="${areatype == 'UM'}"> checked </c:if>>Urban(Municipality Board) Area</label>
                    <label class="radio-inline"><input type="radio" name="UrbanRural" value="R" <c:if test="${areatype == 'R'}"> checked </c:if>>Rural Area</label>
                    </div>

                </div>
                <hr />
                <div class="form-group last">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button id="BtnSave" name="BtnSave" class="btn btn-success btn-sm" type="submit">Submit</button>
                        <button id="BtnClear" name="BtnClear" class="btn btn-default btn-sm" type="reset">Clear</button>
                    </div>
                </div>

            </div>
        </form>
    <c:if test="${successMessage != null}">
        <div class="notice notice-danger col-md-offset-3 col-md-6">
            <c:if test="${conAmount != null}">
                <strong>Consideration Amount (in Rupees):</strong> ${conAmount}<br />
            </c:if>
            <strong>Stamp Duty (in Rupees):</strong> ${stampDuty}<br />
            <strong>Registration Fees (in Rupees):</strong> ${regFee}<br />
        </div>
    </c:if>  

</div>
<script src="js/form-validator/jquery.form-validator.min.js" type="text/javascript"></script>
<script>$.validate({});</script>



