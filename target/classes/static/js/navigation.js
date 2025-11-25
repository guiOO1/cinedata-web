// Funções básicas de navegação - Mantidas iguais, mas agora os links são gerenciados pelo Thymeleaf
function reservarIngresso(sessaoId) {
    console.log('Redirecionando para reserva da sessão:', sessaoId);
    // O link agora é gerenciado pelo Thymeleaf no HTML
}

function voltarParaSessoes() {
    window.location.href = '/sessoes';
}

function irParaHome() {
    window.location.href = '/';
}