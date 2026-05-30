document.addEventListener("DOMContentLoaded", function () {
    const modal = document.getElementById("modalVerificacao");
    const linkVerificacao = document.querySelector(".link-verificacao");
    const closeModalButton = document.querySelector(".close-modal");
    const cancelButton = document.querySelector(".botao-cancelar");
    const selfieInput = document.getElementById("selfieUpload");
    const documentoInput = document.getElementById("documentoUpload");

    if (!modal) {
        return;
    }

    function abrirModal() {
        modal.style.display = "flex";
    }

    function fecharModal() {
        modal.style.display = "none";
    }

    function previewImagem(input, previewId) {
        const file = input.files && input.files[0];
        const previewDiv = document.getElementById(previewId);
        if (!previewDiv) {
            return;
        }

        if (file && file.type && file.type.match("image.*")) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewDiv.innerHTML = '<img src="' + e.target.result + '" style="max-width: 200px; max-height: 200px; border-radius: 8px; margin-top: 10px;">';
            };
            reader.readAsDataURL(file);
            return;
        }

        previewDiv.innerHTML = "";
    }

    function handleInputPreview(input, previewId) {
        input.addEventListener("change", function () {
            previewImagem(input, previewId);
        });
    }

    function setupDropZone(zone, input, previewId) {
        if (!zone || !input) {
            return;
        }

        zone.addEventListener("dragover", function (e) {
            e.preventDefault();
            zone.style.backgroundColor = "#f0f0f0";
        });

        zone.addEventListener("dragleave", function () {
            zone.style.backgroundColor = "";
        });

        zone.addEventListener("drop", function (e) {
            e.preventDefault();
            zone.style.backgroundColor = "";

            const file = e.dataTransfer && e.dataTransfer.files && e.dataTransfer.files[0];
            if (!file) {
                return;
            }

            try {
                const dataTransfer = new DataTransfer();
                dataTransfer.items.add(file);
                input.files = dataTransfer.files;
                input.dispatchEvent(new Event("change", { bubbles: true }));
            } catch (error) {
                // Fallback para navegadores com restricao em DataTransfer.
                previewImagem({ files: [file] }, previewId);
            }
        });
    }

    if (linkVerificacao) {
        linkVerificacao.addEventListener("click", function (e) {
            e.preventDefault();
            abrirModal();
        });
    }

    if (closeModalButton) {
        closeModalButton.addEventListener("click", fecharModal);
    }

    if (cancelButton) {
        cancelButton.addEventListener("click", fecharModal);
    }

    window.addEventListener("click", function (event) {
        if (event.target === modal) {
            fecharModal();
        }
    });

    if (selfieInput) {
        handleInputPreview(selfieInput, "selfiePreview");
        setupDropZone(selfieInput.parentElement, selfieInput, "selfiePreview");
    }

    if (documentoInput) {
        handleInputPreview(documentoInput, "documentoPreview");
        setupDropZone(documentoInput.parentElement, documentoInput, "documentoPreview");
    }
});

