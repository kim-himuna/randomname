'use strict';
$(function(){
    $("[id^='likebtn_']").click(function(){
        var $this = $(this);
        var packid = $this.data('packid');

        $.ajax({
            type:"POST",
            url: "/like",
            data: {
                packid: $this.data("packid"),
            }
        }).done(function(note) {
            $('#likebtn_'+packid).html(note);
            $('#likebtn_'+packid+'likedpack').html(note);
        }).fail(function() {
            alert("通信に失敗しました。")
        }).always(function() {
        })
    });
});