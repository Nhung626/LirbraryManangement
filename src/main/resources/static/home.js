// menu khi trỏ chuột vào thông tin
document.addEventListener('DOMContentLoaded', () => {
    const caretDownIcon = document.querySelector('.thongtin ion-icon[name="caret-down-outline"]');
    const thongTinMenu = document.querySelector('.thongtin-menu');

    caretDownIcon.addEventListener('mouseover', () => {
        thongTinMenu.style.visibility = 'visible';
    });

    thongTinMenu.addEventListener('mouseleave', () => {
        thongTinMenu.style.visibility = 'hidden';
    });
});



// JavaScript
document.addEventListener('DOMContentLoaded', () =>{
    // Thêm sự kiện cho button thể loại
    const theloaiButton = document.getElementById("theloai-button");
    const theloaiMenu = document.getElementById("theloai-menu");

    theloaiButton.addEventListener("mouseover", function() {
        theloaiMenu.style.display = "block";
    });

    theloaiButton.addEventListener("mouseout", function() {
        theloaiMenu.style.display = "none";
    });

    // Thêm sự kiện cho button tác giả
    const tacgiaButton = document.getElementById("tacgia-button");
    const tacgiaMenu = document.getElementById("tacgia-menu");

    tacgiaButton.addEventListener("mouseover", function() {
        tacgiaMenu.style.display = "block";
    });

    tacgiaButton.addEventListener("mouseout", function() {
        tacgiaMenu.style.display = "none";
    });
});