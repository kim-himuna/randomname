'use strict';
$(function(){
    $("[id^='usebtn_']").click(function(){
        var $this = $(this);
        var packid = $this.data('packid');

        $.ajax({
            type:"POST",
            url: "/uselist",
            data: {
                packid: $this.data("packid"),
            }
        }).done(function(note) {
            $('#usebtn_'+packid).html(note);
        }).fail(function() {
            alert("通信に失敗しました。")
        }).always(function() {
        })
    });
});