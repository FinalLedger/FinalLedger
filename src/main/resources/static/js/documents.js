/*<![CDATA[*/

window.addEventListener('DOMContentLoaded', function () {

    // const apikey = /*[[${fileStackAPI}]]*/ 'Api Key';
    let apikey = document.querySelector('meta').content;
    const client = filestack.init(apikey);

    const options = {
        onUploadDone: (res) => {
            $("#uploadDoc").val(res.filesUploaded[0].url);
            console.log(res);
        },
        accept: ["image/*", ".pdf", ".video/*", "text/*"],
        uploadConfig: {
            retry: 5,
            timeout: 60000
        },
        transformations: {
            crop: true,
            circle: true,
            rotate: true
        },
    };

    function uploadDoc() {
      
        client.picker(options).open();
    }

    document.getElementById('buttonUpload').onclick = uploadDoc;

});
/*]]>*/
