<a style="padding: 0px 6px;" href="index.jsp"><span class="glyphicon glyphicon-backward"></span> Go Back</a>
<div class="panel panel-info" style="margin-top: 10px;" >
    <div class="panel-heading">
        <h2 class="panel-title">Check your appointment Status</h2>
    </div>
    <div class="panel-body">
        <c:if test="${errormsg ne null}">
            <div class="alert-danger" style="margin: 10px 0;padding: 10px;">
                <c:forEach var="error" items="${errormsg}">
                    <strong>>></strong> ${error}<br/>
                </c:forEach>
            </div>
        </c:if>
        <form class="form-horizontal" role="form" action="appointment_status" method="post">
            <div class="form-group">
                <label for="DocRefNo" class="col-sm-4 control-label">
                    Reference No <span class="glyphicon glyphicon-star"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="DocRefNo" name="DocRefNo" value="${docrefno}" placeholder="" data-validation="required custom length" data-validation-length="max100" data-validation-regexp="^([a-zA-Z +0-9~%.,:_\-/@&()]+)$" data-validation-error-msg-length="Maxixmum 100 characters allowed"  data-validation-error-msg-required="Required field" data-validation-error-msg-custom="Allowed characters are A to Z, a to z, 0 to 9, ~, %, ., :, _, -, (, ) or blank space" >
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="captcha"> Security Question <span class="glyphicon glyphicon-star"></span>&nbsp;&nbsp;${ran1} + ${ran2} = </label>
                <div class="col-sm-1">
                    <input id="captcha" name="captcha" type="text" placeholder="" class="form-control" data-validation="number" data-validation-error-msg="Invalid value for Security Question">
                </div>
                <div class="captcha-text"> << Specify correct result of addition in the given box</div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-2">
                    <button type="submit" class="btn btn-success btn-sm">Check Status</button>
                </div>
            </div>
        </form>
        <div class="col-sm-offset-4 text-success"><c:if test="${not empty appointment_fee_structure}">${appointment_fee_structure}</c:if></div>
        <div class="col-sm-offset-4 text-success "><c:if test="${not empty appointment_error}">${appointment_error}</c:if></div>
    </div>
</div>
<script src="js/form-validator/jquery.form-validator.min.js" type="text/javascript"></script>
<script>$.validate({});</script>


