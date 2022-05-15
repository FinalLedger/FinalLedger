function decodeJwtResponse(credential) {

    var base64Url = credential.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    console.log(jsonPayload);

    return JSON.parse(jsonPayload);
}

function handleCredentialResponse(response) {
    // decodeJwtResponse() is a custom function defined by you to decode the credential response.
    const responsePayload = decodeJwtResponse(response.credential);

    console.log("ID: " + responsePayload.sub);
    console.log('Full Name: ' + responsePayload.name);
    console.log('Given Name: ' + responsePayload.given_name);
    console.log('Family Name: ' + responsePayload.family_name);
    console.log("Image URL: " + responsePayload.picture);
    console.log("Email: " + responsePayload.email);

}

function signOut() {
    gapi.auth2.getAuthInstance().signOut().then(function () {
        console.log('user has signed out')
    })
}