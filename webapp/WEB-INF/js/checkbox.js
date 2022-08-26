const disableCheckBox = document.getElementById("checkbox");
const button = document.getElementById("btn");

    disableCheckBox.addEventListener("change", (e) => {
        button.disabled = !!(e.target.on);
    });
