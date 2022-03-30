((document, window) => {
  const inputResumo = document.getElementById("resumo");
  const inputTitulo = document.getElementById("titulo");
  const inputPubicado = document.getElementById("publicado");
  const submitButton = document.getElementById("submit-button");

  async function createArticle() {
    const article = {
      resumo: inputResumo.value,
      titulo: inputTitulo.value,
      publicado: inputPubicado.checked,
    };

    console.log(article);

    await fetch("http://localhost:8080/api/artigos", {
      method: "POST",
      body: article,
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  submitButton.addEventListener("click", createArticle);
})(document, window);
