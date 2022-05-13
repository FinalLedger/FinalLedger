function onSignIn(googleUser) {

    var profile = googleUser.getBasicProfile()

    console.log('User is ' + JSON.stringify(profile));

    var element = document.querySelector('#content');
    element.innerText = googleUser.getBasicProfile().getName();

    var image = document.createElement('img')
    image.setAttribute('src', profile.getImageUrl())
}

function signOut() {
    gapi.auth2.getAuthInstance().signOut().then(function () {
        console.log('user has signed out')
    })
}