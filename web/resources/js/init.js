/**
 * Created by benutzer on 12/7/14.
 */
window.onload = function() {
    var url = window.location.pathname;
    var filename = url.substring(url.lastIndexOf('/') + 1);

    switch (filename) {
        case "profile.xhtml":
            setup(document.getElementById("profile"));
            break;
        case "password.xhtml":
            setup(document.getElementById("password"));
            break;
        case "devices.xhtml":
            setup(document.getElementById("devices"));
            break;
    }
}

function setup(element) {
    element.removeAttribute("href");
    element.setAttribute("class", "active");

}