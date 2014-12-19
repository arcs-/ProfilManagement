/**
 * Created by Patrick Stillhart on 12/13/14.
 */
$(document).ready(function() {
    $('.delete').first().hide();

    // Sorry about this, but blame JSF

    $('table tr').click(function() {
        $('.delete').first().show();
        $('h1:eq(1)').first().text('Edit device');
        if($(this).find("td:eq(0)").text().trim()=='true') $(".formBlock:eq(0) input").prop('checked', true);
        else $(".formBlock:eq(0) input").prop('checked', false);

        $('.formBlock:eq(1) input').val( $(this).find("td:eq(1)").text().trim() );
        $('.formBlock:eq(2) input').val( $(this).find("td:eq(2)").text().trim() );
        $('input[name="formContainer:huk"]').val( $(this).find("td:eq(2)").text().trim() );
    });

    $("input[type='reset']").click( function() {
        $('h1:eq(1)').first().text('New device');
        $('.delete').first().hide();
        $('input[name="formContainer:huk"]').val('');
        $('.formBlock:eq(1) input').val('');
        $('.formBlock:eq(2) input').val('');
    });

});