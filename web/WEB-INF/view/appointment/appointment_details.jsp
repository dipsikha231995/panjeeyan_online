
<div class="panel panel-info">
    <div class="panel-heading">
        <h2 class="panel-title">Your appointment is confirmed</h2>
    </div>
    <br>
    <div class="panel-body">
        <div class="col-md-12">
            <div class="alert-message alert-message-success">
                <div style="width:80%;">
                    Your Appointment ID is <b>${appointment_id}</b>. Your appointment is confirmed. Please note this Appointment ID for future reference. <!--Date and time of appointment is already sent to your email ID and mobile number.-->
                </div>
                <div>

                    ${appointment_fee_structure}
                </div>
                <div style="padding-bottom: 10px"><input class="btn btn-success btn-sm" type="button" value="Print" onclick="PrintElem('.appointment_details', 'Appointment Details')" /></div>
            </div>
        </div>
    </div>
</div>
<