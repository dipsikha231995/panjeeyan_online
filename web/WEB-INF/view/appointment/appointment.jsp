<script>
    $(document).ready(function () {
        if ($("#Deedtype").val() !== -1) {
            var code = $("#Deedtype").val();
            var subdeed = $("#Subdeedtype").val();
            $.post("http://" + location.host + "/panjeeyanonline/subdeedbydeed", {id: code, subdeed: subdeed}, function (values) {
                $("#Subdeedtype").html(values);
            });
        }
        $("#Deedtype").change(function () {
            var code = $("#Deedtype").val();
            $.post("http://" + location.host + "/panjeeyanonline/getsubdeed", {id: code}, function (values) {
                if(values!="Error"){
                    $("#Subdeedtype").html(values);
                    
                }else{
                    alert("Value specified for field Deed Category is not valid.");
                    $("#Subdeedtype").html("<option></option>");
                }
                
            });
        });
        $("#BtnPrint").click(function () {
            var message = $("#Message");
            var code = message.attr('value');
            $.post("http://" + location.host + "/panjeeyanonline/printslip", {id: code}, function (values) {

                var win = window.open('about:blank');
                with (win.document)
                {
                    open();
                    write(values);
                    close();
                }
            });
        });
    });
</script> 

<a style="padding: 0px 6px;" href="index.jsp"><span class="glyphicon glyphicon-backward"></span> Go Back</a>

<div class="panel panel-info"  style="margin-top: 10px;">
    <div class="panel-heading">
        <h2 class="panel-title">Form for Taking an Appointment</h2>
    </div>
    <div class="panel-body">
        <% 
            //session.removeAttribute("errormsg"); 
        %>
        <c:if test="${errormsg ne null}">
            <div class="alert-danger" style="margin: 10px 0;padding: 10px;">
                <c:forEach var="error" items="${errormsg}">
                    ${error}<br/>
                </c:forEach>
            </div>
            <% 
              session.removeAttribute("errormsg"); 
            %>
        </c:if>
        
        
        <form class="form-horizontal" method="POST" action="create_appointment" name="frmenquiry" enctype="multipart/form-data">
            <div class="form-group">
                ${appointment_failure}
                <label class="col-sm-4 control-label" for="ApplicantName">Name of the applicant  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input id="ApplicantName" name="ApplicantName" type="text" placeholder="" class="form-control"  value="${applicant}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space">
                </div>
            </div>

            <!-- Email -->
            <div class="form-group">
                <label class="col-sm-4 control-label" for="email">e-mail  <span style="visibility: hidden" class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-3">
                    <input id="email" name="email" type="text" placeholder="" class="form-control" value="${email}" data-validation="custom length" data-validation-length="max30" data-validation-regexp="(^$|^.*@.*\..*$)"  data-validation-error-msg-length="Maxixmum 30 characters allowed"  data-validation-error-msg-custom="Invalid Email">
                </div>
            </div>
            <!-- Mobile number -->
            <div class="form-group">
                <label class="col-sm-4 control-label" for="mobile_number">Mobile number  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-2">
                    <input id="mobile_number" name="mobile_number" type="text" placeholder="" class="form-control" value="${mobile}" data-validation="required custom" data-validation-regexp="^[0-9]{10,10}$" data-validation-error-msg="Required field" data-validation-error-custom="Mobile number should contain only 10 digits" >
                </div>
            </div>
            <!-- Applicant Address -->
            <div class="form-group">
                <label class="col-sm-4 control-label" for="applicant_address">Address (House No/Street/Locality) <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-6">
                    <input id="applicant_address" name="applicant_address" type="text" placeholder="Specify House No/Street No/Locality e.g. House No 12, Bylane 2, Jonaki Nagar" class="form-control" value="${address}" data-validation="required custom length" data-validation-length="max100" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 100 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="applicant_city_vill">City/Town/Village  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-3">
                    <input id="applicant_address" name="applicant_city_vill" type="text" placeholder="" class="form-control" value="${cityvill}" data-validation="required custom length" data-validation-length="max30" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 30 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="applicant_post_office">Post Office<span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-3">
                    <input id="applicant_address" name="applicant_post_office" type="text" placeholder="" class="form-control" value="${postoff}" data-validation="required custom length" data-validation-length="max30" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 30 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-sm-4 control-label" for="applicant_district">District <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-3">
                    <input id="applicant_address" name="applicant_district" type="text" placeholder="" class="form-control" value="${district}" data-validation="required custom length" data-validation-length="max30" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 30 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-sm-4 control-label" for="applicant_pin">PIN <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-2">
                    <input id="applicant_address" name="applicant_pin" type="text" placeholder="" class="form-control" value="${pin}" data-validation="required custom" data-validation-regexp="^([1-9])([0-9]){5}$" data-validation-error-msg-custom="PIN code should be 6 digit numeric value"  data-validation-error-msg-required="Required field" />
                </div>
            </div>
            <!-- Applicant Type -->
            <div class="form-group">
                <label class="col-sm-4 control-label" for="applicant_type">Applicant Type  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <select id="applicant_type" name="applicant_type" class="form-control" data-validation="required" data-validation-error-msg="Select Applicant type">
                        <option value="">Select Applicant type</option>
                        <option value="0">Urgent</option>
                        <c:forEach var="applicantTypes" items="${applicantTypes}">
                            <option  value="${applicantTypes.exempted}" <c:if test="${applicantTypes.exempted == apptype}"> selected="selected" </c:if>>${applicantTypes.type}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <!-- Appointment date  -->
            <div class="form-group">
                <label class="col-sm-4 control-label"  for="appointment_date">Select Desired Appointment date  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <select id="appointment_date" name="appointment_date" class="form-control" data-validation="required" data-validation-error-msg="Select a Date">
                        <option value="">Click here to select</option>
                        <c:forEach var="date" items="${dates}">
                            <option  value="${date}" <c:if test="${date == appdate}"> selected="selected" </c:if>>${date}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="sro_office">Select Office for Registration   <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <select id="sro_office" name="sro_office" class="form-control" data-validation="required" data-validation-error-msg="Select Office for Registration">
                        <option value="">Please select a option</option>
                        <c:forEach var="SroOffice" items="${SroOffices}">
                            <option  value="${SroOffice.id}" <c:if test="${SroOffice.id == sro}"> selected="selected" </c:if>>${SroOffice.officeName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="DeedType">Deed Category  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    
                    <select id="Deedtype" name="Deedtype" class="form-control" data-validation="required" data-validation-error-msg="Select Deed Type">
                        <option value="">Click here to Select</option>
                        <c:forEach var="deedList" items="${deedList}">  
                            <option value="${deedList[0]}">${deedList[1]}</option>
                       </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="Subdeedtype">Deed Sub-category   <span class="glyphicon glyphicon-star"></span> </label>
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
                <label class="col-sm-4 control-label" for="captcha"> Security Question &nbsp;&nbsp;${ran1} + ${ran2} = </label>
                <div class="col-sm-1">
                    <input id="captcha" name="captcha" type="text" placeholder="" class="form-control" data-validation="number" data-validation-error-msg="Invalid value for Security Question" >
                </div>
                <div class="captcha-text"> << Specify correct result of addition in the given box</div>
            </div>
            <hr />
            <h5 style="color: #0099ff;font-weight:bold;text-decoration: underline"> Below information is necessary only in case of deeds related to land/flat (e.g sale or gift of land/flat etc.)</h5> 
            <div class="form-group">

                <div class="form-group">
                    <label class="col-sm-3 control-label" for="ConsiderationAmt">Consideration Amount </label>
                    <div class="col-sm-4">
                        <input id="ConsiderationAmt" name="ConsiderationAmt" type="text" value="0" placeholder="" class="form-control" value="${renquiry.conamount}" data-validation="number" data-validation-error-msg="Numeric values only">
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
                        <div class="col-sm-offset-3 col-sm-6">
                            <button id="BtnSave" name="BtnSave" class="btn btn-success btn-sm" type="submit">Submit</button>
                            <button id="BtnClear" name="BtnClear" class="btn btn-default btn-sm" type="reset">Clear</button>
                        </div>
                    </div>

                </div>
            </form>
            <script src="js/form-validator/jquery.form-validator.min.js" type="text/javascript"></script>
            <script>$.validate({});</script>
        ${successMessage}
    </div>
</div>

