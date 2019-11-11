
/* --------- SLIDE SHOW --------- */

var slideIndex = 1;

var slideshowContainer;

window.addEventListener("load", function () {
  showSlides(slideIndex);
})

// vor und zurück
function plusSlides(n) {
  if (n < 0) {
    showSlides(slideIndex -= 1);
  } else {
    showSlides(slideIndex += 1);
  }
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) { slideIndex = 1 }
  if (n < 1) { slideIndex = slides.length }
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex - 1].style.display = "block";
  dots[slideIndex - 1].className += " active";
}



/* --------- TICKER --------- */

var i0 = 0;
var txt0 = '[Radio-Durchsage:] ... Bürger von Radiation Land! Letzte Nacht ist etwas Erschreckendes passiert.. Ein unbekanntes Objekt aus dem Weltall hat unsere kleine Stadt getroffen und giftige, noch nie dagewesene Gase freigelassen. Wir bitten alle Bürger möglichst schnell einen sicheren Unterschlupf zu suchen. Aufgrund der verseuchten Luft ist es dringend notwendig auf eine ausreichende Sauerstoffzufuhr zu achten. Leider haben es auch einige Mitglieder unserer Gemeinde nicht geschafft der Seuche zu entkommen, sie leben nun in den Wäldern außerhalb der Stadt. Liebe Bürger schützen sie sich selbst. Ich wünsche ihnen viel Glück dabei! ...';
var speed0 = 70;

function typeWriter0() {
  if (i0 < txt0.length) {
    document.getElementById("story-text").innerHTML += txt0.charAt(i0);
    i0++;
    setTimeout(typeWriter0, speed0);
  }
  showButton();
}

function showButton() {
  let btn = document.createElement("button");
  btn.style.position = "absolute";
}




