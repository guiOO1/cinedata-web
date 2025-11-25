// Funcionalidades da página de reservas
document.addEventListener('DOMContentLoaded', function() {
    const assentos = document.querySelectorAll('.assento-btn');
    const reservaForm = document.getElementById('reservaForm');
    const quantidadeSelect = document.getElementById('quantidade');
    const assentosInput = document.getElementById('assentosSelecionados');
    const totalElement = document.getElementById('totalReserva');
    
    let precoUnitario = 20.00; // Será atualizado pelo Thymeleaf

    // Simular alguns assentos ocupados
    simularAssentosOcupados();

    // Seleção de assentos
    assentos.forEach(assento => {
        assento.addEventListener('click', function() {
            // Não permitir selecionar assentos ocupados
            if (this.classList.contains('ocupado')) {
                return;
            }
            
            const assentosSelecionados = document.querySelectorAll('.assento-btn.selecionado');
            const quantidade = parseInt(quantidadeSelect.value);
            
            // Se já atingiu o limite, desmarcar o primeiro selecionado
            if (assentosSelecionados.length >= quantidade && !this.classList.contains('selecionado')) {
                assentosSelecionados[0].classList.remove('selecionado');
            }
            
            this.classList.toggle('selecionado');
            atualizarResumoReserva();
            atualizarInputAssentos();
        });
    });

    // Atualizar quantidade de ingressos
    if (quantidadeSelect) {
        quantidadeSelect.addEventListener('change', function() {
            const assentosSelecionados = document.querySelectorAll('.assento-btn.selecionado');
            const novaQuantidade = parseInt(this.value);
            
            // Se reduziu a quantidade, remover assentos extras
            if (assentosSelecionados.length > novaQuantidade) {
                for (let i = novaQuantidade; i < assentosSelecionados.length; i++) {
                    assentosSelecionados[i].classList.remove('selecionado');
                }
            }
            
            atualizarResumoReserva();
            atualizarInputAssentos();
        });
    }

    // Submit do formulário de reserva
    if (reservaForm) {
        reservaForm.addEventListener('submit', function(event) {
            const assentosSelecionados = document.querySelectorAll('.assento-btn.selecionado');
            const quantidade = parseInt(quantidadeSelect.value);
            
            if (assentosSelecionados.length === 0) {
                event.preventDefault();
                alert('Por favor, selecione pelo menos um assento.');
                return;
            }

            if (assentosSelecionados.length !== quantidade) {
                event.preventDefault();
                alert(`Por favor, selecione exatamente ${quantidade} assento(s) conforme a quantidade de ingressos.`);
                return;
            }

            // Coletar assentos selecionados
            const assentosArray = Array.from(assentosSelecionados).map(assento => 
                assento.getAttribute('data-assento')
            );
            
            console.log('Assentos selecionados:', assentosArray);
            // O formulário será enviado para o backend Spring
        });
    }
});

function simularAssentosOcupados() {
    // Simular alguns assentos já ocupados
    const assentosOcupados = ['A1', 'B4', 'C7', 'D2', 'E5', 'F3', 'G6'];
    
    assentosOcupados.forEach(assentoId => {
        const assento = document.querySelector(`[data-assento="${assentoId}"]`);
        if (assento) {
            assento.classList.add('ocupado');
        }
    });
}

function atualizarResumoReserva() {
    const quantidade = document.getElementById('quantidade')?.value || 1;
    const assentosSelecionados = document.querySelectorAll('.assento-btn.selecionado');
    const precoUnitario = 20.00; // Em produção viria do backend
    const total = quantidade * precoUnitario;
    
    const resumoElement = document.querySelector('.resumo-reserva');
    const totalElement = document.getElementById('totalReserva');
    
    if (resumoElement) {
        let html = `<p><strong>Quantidade: ${quantidade} ingresso(s)</strong></p>`;
        
        if (assentosSelecionados.length > 0) {
            const assentos = Array.from(assentosSelecionados).map(assento => 
                assento.getAttribute('data-assento')
            ).join(', ');
            html += `<p><strong>Assentos: ${assentos}</strong></p>`;
        } else {
            html += `<p><strong>Assentos: Nenhum selecionado</strong></p>`;
        }
        
        html += `<p><strong>Total: R$ ${total.toFixed(2)}</strong></p>`;
        resumoElement.innerHTML = html;
    }
    
    if (totalElement) {
        totalElement.textContent = total.toFixed(2);
    }
}

function atualizarInputAssentos() {
    const assentosSelecionados = document.querySelectorAll('.assento-btn.selecionado');
    const assentosInput = document.getElementById('assentosSelecionados');
    
    if (assentosInput && assentosSelecionados.length > 0) {
        const assentosArray = Array.from(assentosSelecionados).map(assento => 
            assento.getAttribute('data-assento')
        );
        assentosInput.value = assentosArray.join(',');
    }
}