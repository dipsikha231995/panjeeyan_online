<script>
    $(document).ready(function () {
        $("#IfPresentPermanentBride").click(function () {
            if ($("#IfPresentPermanentBride").val() === '0') {
                $("#BridePermanentVillage").val($('#BrideVillage').val());
                $("#BridePermanentPO").val($("#BridePostOffice").val());
                $("#BridePermanentPS").val($("#BridePoliceStation").val());
                $("#BridePermanentDistrict").val($("#BrideDistrict").val());
                $("#BridePermanentState").val($("#BrideState").val());
                $("#BridePermanentPincode").val($("#BridePincode").val());
            } else {
                $("#IfPresentPermanentBride").val("0");
                $('#panelpermanentaddress input ').val("");
            }
        });
        $("#IfPresentPermanentGroom").click(function () {
            if ($("#IfPresentPermanentGroom").val() === '0') {
                $("#IfPresentPermanentGroom").val("1");
                $("#GroomPermanentVillage").val($('#GroomVillage').val());
                $("#GroomPermanentPO").val($("#GroomPoliceStation").val());
                $("#GroomPermanentPS").val($("#GroomPostOffice").val());
                $("#GroomPermanentDistrict").val($("#GroomDistrict").val());
                $("#GroomPermanentState").val($("#GroomState").val());
                $("#GroomPermanentPincode").val($("#GroomPincode").val());
            } else {
                $("#IfPresentPermanentGroom").val("0");
                $('#panelpermanentaddress input ').val("");
            }
        });

    });
</script>
<a style="padding: 0px 6px;" href="index.jsp"><span class="glyphicon glyphicon-backward"></span> Go Back</a>
<div class="panel panel-info" style="margin-top: 10px;">
    <div class="panel-heading">
        <h2 class="panel-title">Add marriage details</h2>
    </div>
    <c:if test="${errormsg ne null}">
        <div class="alert-danger" style="margin: 10px 0;padding: 10px;">
            <c:forEach var="error" items="${errormsg}">
                <strong>>></strong> ${error}<br/>
            </c:forEach>
        </div>
    </c:if>

    <div class="panel-body">
        <form class="form-horizontal" role="form" action="addmarriagedetails" method="POST">
            <div class="form-group">
                <label class="col-sm-3 control-label"  for="appointment_date">Desired Appointment date  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-3">
                    <select id="appointment_date" name="appointment_date" class="form-control" data-validation="required" data-validation-error-msg="Select a Date">
                        <option value="">Click here to select</option>
                        <c:forEach var="date" items="${dates}">
                            <option  value="${date}" <c:if test="${date == appdate}"> selected="selected" </c:if>>${date}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="MarriageType">Marriage Type <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-3">
                    <select name="MarriageType" class="form-control" id="MarriageType" data-validation="required" data-validation-error-msg="Select Marriage Type">
                        <option value="">Select Marriage Type</option>
                        <c:forEach items="${marriagetypes}" var="type">
                            <option value="${type.marriageType}" <c:if test="${type.marriageType == marriagetype}"> selected="selected" </c:if>>${type.marriageType}</option>
                        </c:forEach>
                    </select>
                </div>
                <label class="col-sm-3 control-label" for="ApplicantName">Name of the Applicant <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-3 ">
                    <input type="text" class="form-control" id="ApplicantName" value="${applicant}" name="ApplicantName" data-validation="required custom length" data-validation-length="max40" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 40 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                </div>
                <label class="col-sm-3 control-label" for="DeedType">Office for Registration  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-3">
                    <select id="srooffice" name="sro_office" class="form-control" data-validation="required" data-validation-error-msg="Select Office for Registration">
                        <option value="">Select SRO Office</option>
                        <c:forEach var="SroOffice" items="${SroOffices}">
                            <option  value="${SroOffice.id}" <c:if test="${SroOffice.id == sro}"> selected="selected" </c:if>>${SroOffice.officeName}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Email -->

                <label class="col-sm-3 control-label" for="email">Email </label>
                <div class="col-sm-3">
                    <input id="email" name="email" type="text" placeholder="" class="form-control" value="${email}" data-validation="custom length" data-validation-length="max30" data-validation-regexp="(^$|^.*@.*\..*$)"  data-validation-error-msg-length="Maxixmum 30 characters allowed"  data-validation-error-msg-custom="Invalid Email"/>
                </div>


                <!-- Mobile number -->

                <label class="col-sm-3 control-label" for="mobile_number">Mobile number  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-3">
                    <input id="mobile" name="mobile" type="text" placeholder="" class="form-control" value="${mobile}" data-validation="required custom" data-validation-regexp="^[0-9]{10,10}$" data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Mobile number should contain only 10 digits" >
                </div>
            </div><br>
            <div class="panel panel-default">
                <div class="panel-heading" style="color: black"><strong>Bride Details</strong></div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-sm-3 col-sm-3 control-label" for="BrideName">Name of Bride <span class="glyphicon glyphicon-star"></span></label>
                        <div class="col-sm-3 ">
                            <input type="text" class="form-control" id="BrideName" name="BrideName" value="${bride}" data-validation="required custom length" data-validation-length="max100" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 100 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                        </div>
                        <label class="col-sm-2 control-label" for="BrideAge">Age <span class="glyphicon glyphicon-star"></span></label>
                        <div class="col-sm-2 ">
                            <input type="text" class="form-control" placeholder="Age" id="BrideAge" name="BrideAge" value="${bage}"  data-validation="required number" data-validation-allowing="range[18;200]" data-validation-error-msg-number="Allowed age range is 18 to 200 years" data-validation-error-msg-required="Required field"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="BrideMarriageCondition">Marital Status <span class="glyphicon glyphicon-star"></span></label>
                        <div class="col-sm-3 ">
                            <select name="BrideMarriageCondition" class="form-control" id="BrideMarriageCondition" data-validation="required" data-validation-error-msg="Select Marriage Condition">
                                <option value="">Select Marriage Condition</option>
                                <c:forEach items="${marriagecondtn}" var="condition">
                                    <option value="${condition.marriageCondn}" <c:if test="${condition.marriageCondn == bmcondition}"> selected="selected" </c:if>>
                                        ${condition.marriageCondn}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-sm-2 control-label" for="BrideOccupation">Occupation <span class="glyphicon glyphicon-star"></span></label>
                        <div class="col-sm-3 ">
                            <input type="text" class="form-control" id="BrideOccupation" name="BrideOccupation" value="${boccupation}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="BrideFathersName">Father's Name <span class="glyphicon glyphicon-star"></span></label>
                        <div class="col-sm-3 ">
                            <input type="text" class="form-control" id="BrideFathersName" name="BrideFathersName" value="${bfather}" data-validation="required custom length" data-validation-length="max100" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 100 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                        </div>
                    </div>
                    <legend><h5>Bride Address</h5></legend>
                    <div style="float:left;width:50%;">

                        <div class="form-group">
                            <label class="col-sm-6 control-label">Present Address</label>
                        </div>
                        <div id="panelpresentaddress">
                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="BrideVillage">Village/City/Locality <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="BrideVillage" name="BrideVillage" value="${bvil}" data-validation="required custom length" data-validation-length="max250" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 250 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="BridePoliceStation">Police Station<span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="BridePoliceStation" name="BridePoliceStation" value="${bpolstat}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="BridePostOffice"> Post Office <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="BridePostOffice" name="BridePostOffice" value="${bpostoff}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="BrideDistrict">District <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="BrideDistrict" name="BrideDistrict" value="${bdist}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="BrideState">State <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="BrideState" name="BrideState" value="${bstate}" data-validation="required custom length" data-validation-length="max30" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 30 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="BridePincode">PIN code <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="BridePincode" name="BridePincode" value="${bpin}" data-validation="required custom" data-validation-regexp="^([1-9])([0-9]){5}$" data-validation-error-msg-custom="PIN code should be 6 digit numeric value"  data-validation-error-msg-required="Required field"/>
                                </div>
                            </div>

                        </div></div>
                    <!----------------->
                    <div style="float:right;width:50%;">
                        <div class="form-group">
                            <label class="col-sm-8 control-label" for="IfPresentPermanentBride">Permanent Address (If same as Present)</label>
                            <div class="col-sm-3">
                                <input type="checkbox" name="IfPresentPermanentBride" id="IfPresentPermanentBride" value="0">
                            </div>
                        </div>
                        <div id="panelpermanentaddress">
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="BridePermanentVillage">Village/City/Locality <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="BridePermanentVillage" name="BridePermanentVillage" value="${permbvil}" data-validation="required custom length" data-validation-length="max250" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 250 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>



                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="BridePermanentPS">Police Station <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="BridePermanentPS" name="BridePermanentPS" value="${permbpolstat}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="BridePermanentPO">Post Office <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="BridePermanentPO" name="BridePermanentPO" value="${permbpostoff}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="BridePermanentDistrict">District <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="BridePermanentDistrict" name="BridePermanentDistrict" value="${permbdist}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="BridePermanentState">State <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="BridePermanentState" name="BridePermanentState" value="${permbstate}" data-validation="required custom length" data-validation-length="max30" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 30 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="BridePermanentPincode">Pin code <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="BridePermanentPincode" name="BridePermanentPincode" value="${permbpin}" data-validation="required custom" data-validation-regexp="^([1-9])([0-9]){5}$" data-validation-error-msg-custom="PIN code should be 6 digit numeric value"  data-validation-error-msg-required="Required field"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-7 control-label" for="BrideLengthOfResidence">Length of residence at present address in years </label>
                            <div class="col-sm-3 ">
                                <input type="text" class="form-control" id="BrideLengthOfResidence" name="BrideLengthOfResidence" value="${blenthofres}" data-validation="number" data-validation-allowing="range[0;200],float" data-validation-error-msg="Only Numeric values allowed from 0 to 200"/>
                            </div>
                        </div>
                    </div>

                </div></div>
            <!--------------------->
            <div class="panel panel-default">
                <div class="panel-heading" style="color: black">Groom  Details</div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="GroomName">Name of Groom <span class="glyphicon glyphicon-star"></span></label>
                        <div class="col-sm-3 ">
                            <input type="text" class="form-control" id="GroomName" name="GroomName" value="${groom}" data-validation="required custom length" data-validation-length="max100" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 100 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                        </div>
                        <label class="col-sm-2 control-label" for="GroomAge">Age <span class="glyphicon glyphicon-star"></span></label>
                        <div class="col-sm-2 ">
                            <input type="text" class="form-control" id="GroomAge" name="GroomAge" value="${gage}" data-validation="number" data-validation-allowing="range[21;100]" data-validation-error-msg="Age should not less than 21"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="GroomMarriageCondition">Marital Status <span class="glyphicon glyphicon-star"></span></label>
                        <div class="col-sm-3 ">
                            <select name="GroomMarriageCondition" class="form-control" id="GroomMarriageCondition" data-validation="required"  data-validation-error-msg="Select Marriage Condition">
                                <option value="">Select Marriage Condition</option>
                                <c:forEach items="${marriagecondtn}" var="condition">
                                    <option value="${condition.marriageCondn}" <c:if test="${condition.marriageCondn == gmcondition}"> selected="selected" </c:if>>
                                        ${condition.marriageCondn}</option>
                                    </c:forEach>
                            </select>
                        </div>
                        <label class="col-sm-2 control-label" for="GroomOccupation">Occupation <span class="glyphicon glyphicon-star"></span></label>
                        <div class="col-sm-3 ">
                            <input type="text" class="form-control" id="GroomOccupation" name="GroomOccupation" value="${goccupation}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="GroomFathersName">Father's Name <span class="glyphicon glyphicon-star"></span></label>
                        <div class="col-sm-3 ">
                            <input type="text" class="form-control" id="GroomFathersName" name="GroomFathersName" value="${gfather}" data-validation="required custom length" data-validation-length="max100" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 100 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                        </div>
                    </div>
                    <legend><h5>Groom Address</h5></legend>
                    <div style="float:left;width:50%;">
                        <div id="panelpresentaddress">
                            <div class="form-group">
                                <label class="col-sm-6 control-label">Present Address</label>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="GroomVillage">Village/City/Locality <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="GroomVillage" name="GroomVillage" value="${gvil}" data-validation="required custom length" data-validation-length="max250" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 250 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="GroomPoliceStation">Police Station <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="GroomPoliceStation" name="GroomPoliceStation" value="${gpolstat}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="GroomPostOffice">Post Office <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="GroomPostOffice" name="GroomPostOffice" value="${gpostoff}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="GroomDistrict">District <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="GroomDistrict" name="GroomDistrict" value="${gdist}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="GroomState">State <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="GroomState" name="GroomState" value="${gstate}" data-validation="required custom length" data-validation-length="max30" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 30 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-6 control-label" for="GroomPincode">Pin code <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="GroomPincode" name="GroomPincode" value="${gpin}" data-validation="required custom" data-validation-regexp="^([1-9])([0-9]){5}$" data-validation-error-msg-custom="PIN code should be 6 digit numeric value"  data-validation-error-msg-required="Required field"/>
                                </div>
                            </div>

                        </div></div>
                    <div style="float:right;width:50%;">
                        <div class="form-group">
                            <label class="col-sm-8 control-label" for="IfPresentPermanentGroom">Permanent Address (If same as Present)</label>
                            <div class="col-sm-3">
                                <input type="checkbox" name="IfPresentPermanentGroom" id="IfPresentPermanentGroom" value="0">
                            </div>
                        </div>
                        <div id="panelpermanentaddress">
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="GroomPermanentVillage">Village/City/Locality <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="GroomPermanentVillage" name="GroomPermanentVillage" value="${permgvil}" data-validation="required custom length" data-validation-length="max250" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 250 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="GroomPermanentPO">Post Office <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="GroomPermanentPO" name="GroomPermanentPO" value="${permgpostoff}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="GroomPermanentPS">Police Station <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="GroomPermanentPS" name="GroomPermanentPS" value="${permgpolstat}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="GroomPermanentDistrict">District <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="GroomPermanentDistrict" name="GroomPermanentDistrict" value="${permgdist}" data-validation="required custom length" data-validation-length="max50" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 50 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="GroomPermanentState">State <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="GroomPermanentState" name="GroomPermanentState" value="${permgstate}" data-validation="required custom length" data-validation-length="max30" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-@&()]+)$" data-validation-error-msg-length="Maxixmum 30 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="GroomPermanentPincode">Pin code <span class="glyphicon glyphicon-star"></span></label>
                                <div class="col-sm-6 ">
                                    <input type="text" class="form-control" id="GroomPermanentPincode" name="GroomPermanentPincode" value="${permgpin}" data-validation="required custom" data-validation-regexp="^([1-9])([0-9]){5}$" data-validation-error-msg-custom="PIN code should be 6 digit numeric value"  data-validation-error-msg-required="Required field"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-7 control-label" for="gLengthOfResidence">Length of residence at present address in years</label>
                                <div class="col-sm-3 ">
                                    <input type="text" class="form-control" id="gLengthOfResidence" name="gLengthOfResidence" value="${glenthofres}" data-validation="number" data-validation-allowing="range[0;200],float" data-validation-error-msg="Only Numeric values allowed from 0 to 200"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="captcha"> Security Question &nbsp;&nbsp;${ran1} + ${ran2} = </label>
                <div class="col-sm-1">
                    <input id="captcha" name="captcha" type="text" placeholder="" class="form-control" >
                </div>
                <div class="captcha-text"> << Specify correct result of addition in the given box</div>
            </div>
            <div style="margin-top: 10px" class="form-group last">
                <div class="col-sm-offset-3 col-sm-4">
                    <button type="submit" class="btn btn-success btn-sm">Submit</button>
                    <button type="reset" class="btn btn-default btn-sm">Reset</button>
                </div>
            </div>

        </form>    
    </div>
</div> 

<script src="js/form-validator/jquery.form-validator.min.js" type="text/javascript"></script>
<script>
$.validate({});
</script>