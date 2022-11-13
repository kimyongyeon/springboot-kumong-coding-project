class Register {

    alertWarring(msg){
        $(this.alert).removeClass('d-none').html(msg);
        setTimeout(function(){
            $(this.alert).addClass('d-none');
        }, 1000);
    }

    newImagePreview(files) {
        // * 이미지 저장
        const saveImage = (files) => {
            return URL.createObjectURL(files[0]);
        };
        // * 이미지 삭제
        const deleteImage = (image) => {
            return URL.revokeObjectURL(image);
        };

        let vUrl = saveImage(files);
        let uploadUrl = $(".uploadResult ul");
        const str = `
                <li>
                    <div class="card" style="width: 18rem;">
                      <img src="${vUrl}" class="card-img-top" alt="thumbnail">
                      <div class="card-body">
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                      </div>
                      <button type="button" class="btn-warning btn-sm">X</button><br>
                    </div>
                </li>

            `;
        $(uploadUrl).append(str);
    }

    oldImagePreview(files) {
        let regex = new RegExp("(.*?)\.(exe|sh|zip|alz|tiff)$");
        let maxSize = 10485760; // 10MB
        function checkExtension(fileName, fileSize) {
            if (fileSize >= maxSize) {
                alert("파일 사이즈 초과");
                return false;
            }
            if (regex.test(fileName)) {
                alert("해당 종류의 파일은 업로드할 수 없습니다. ");
                return false;
            }
            return true;
        }

        let appended = false;
        let formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            console.log(files[i]);
            if (!checkExtension(files[i].name, files[i].size)) {
                return false;
            }
            formData.append("uploadFiles", files[i]);
            appended = true;
        }
        // upload를 하지 않는다.
        if (!appended) {
            return;
        }
        for (let value of formData.values()) {
            console.log(value);
        }
        // 실제 업로드 부분
        fetch('/uploadFetch', {
            method: 'POST',
            body: formData
        })
            .then((response) => response.json())
            .then((result) => {
                console.log('Success:', result);

                function showResult(uploadResultArr) {
                    let uploadUrl = $(".uploadResult ul");
                    let str = "";
                    $(uploadResultArr).each((i, obj) => {
                        str = `
                            <li data-name="${obj.fileName}" data-path="${obj.folderPath}" data-uuid="${obj.uuid}">
                                <div class="card" style="width: 18rem;">
                                  <img src="/display?fileName=${obj.thumbnailUrl}" class="card-img-top" alt="thumbnail">
                                  <div class="card-body">
                                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                  </div>
                                  <button type="button" data-file="${obj.imageUrl}" class="btn-warning btn-sm">X</button><br>
                                </div>
                            </li>

                        `
                    })
                    $(uploadUrl).append(str);
                }

                showResult(result);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    constructor({alert, fileInput, uploadResult, submit}) {
        setTimeout(function() {
            $(alert).addClass('d-none');
        }, 1000);

        this.fileInput = fileInput;
        this.uploadResult = uploadResult;
        this.submit = submit;
        this.alert = alert;

        this.eventOn();
    }

    eventOn() {
        $(this.fileInput).on("change", $.proxy(this.onChangeFileUpload, this));
        $(this.uploadResult).on("click", $.proxy(this.onClickUploadResult, this));
        $(this.submit).on("click", $.proxy(this.onClickSubmit, this));

        $(".custom-event").on("click", function() {
            $(".custom-event").trigger("viewRender", {name: 'hello'});
        });

        $(".custom-event").on("viewRender", function(e, data) {
            alert(data.name);
        })

        $(this.fileInput).on("viewRender", function(e, data) {
            alert('fileInput hello ' + data.name);
        })
    }

    onChangeFileUpload(e) {
        let inputFile = $("input[name='uploadFiles']");
        let files = inputFile[0].files;
        this.newImagePreview(files);
        // $(this).trigger("viewRender", {name: 'fire'});
    }
    onClickUploadResult(e) {
        console.log("delete file");
        let targetFile = $(this).data("file");
        let targetLi = $(this).closest("li");
        $.ajax({
            url: "/removeFile",
            data: {fileName: targetFile},
            dataType: "text",
            type: "POST",
            success: (result) => {
                // alert(result);
                this.alertWarring(result);
                targetLi.remove();
            },
        })
    }
    onClickSubmit(e) {
        // e.preventDefault();
        let str = "";
        $(".uploadResult li").each(function(i, obj) {
            let target = $(obj);
            str = `
                <input type="hidden" name="imageDTOList[${i}].imgName" value="${target.data('name')}">
                <input type="hidden" name="imageDTOList[${i}].path" value="${target.data('path')}">
                <input type="hidden" name="imageDTOList[${i}].uuid" value="${target.data('uuid')}">
            `
        });
        // 태그들이 추가된 것을 확인 후에 comment를 제거
        $(".box").html(str);
        $("form").submit();
    }

}