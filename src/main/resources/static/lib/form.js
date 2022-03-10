((document, window) => {
  const button = document.querySelector("#botao");
  button.addEventListener("click", () => {
    createArticle();
  });

  async function createArticle() {
    const article = {
      resumo: "teste",
      titulo: "teste",
      publicado: true,
    };

    await fetch("http://localhost:8080/api/artigos", {
      method: "POST",
      body: article,
      headers: {
        "Content-Type": "application/json",
      },
    });
  }
})(document, window);
