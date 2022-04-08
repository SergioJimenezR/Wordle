var palabraSecreta = '';
var filaActual = 0;
var palabraFormada = '';
var continuar = true;

var celdasTotales = Array.from(document.querySelectorAll('td'));

nuevaPalabra();

function nuevaPalabra() {
	var peticion = new XMLHttpRequest();
	peticion.open('GET', 'wordle/getNuevaPalabra');
	peticion.onload = function() {
		console.log("Solución: " + (palabraSecreta = this.responseText));
	}
	peticion.send();
}

document.addEventListener('keyup', function(evento) {
	if (palabraFormada.length <= 4 && evento.key.length === 1 && evento.key.match(/[a-zA-ZñÑ]/) && continuar) {
		palabraFormada += evento.key.toUpperCase();

		if (palabraFormada.length == 5) {
			var peticion = new XMLHttpRequest();
			peticion.open('GET', 'wordle/existePalabra?palabra=' + palabraFormada);
			peticion.onload = function() {
				console.log('La palabra ' + palabraFormada + (this.responseText == 'true' ? ' sí' : ' no') + ' existe.');
				if (this.responseText == 'true') {
					for (var i = 0; i < celdas.length; i++)
						if (palabraSecreta.charAt(i) == palabraFormada.charAt(i))
							celdas[i].style.background = '#6aaa64'; // Verde
						else if (palabraSecreta.includes(palabraFormada.charAt(i)))
							celdas[i].style.background = '#c9b458'; // Amarillo
						else
							celdas[i].style.background = '#787c7e'; // Gris

					if (palabraSecreta == palabraFormada) {
						console.log('¡Objetivo alcanzado en el ' + (filaActual + 1) + 'º intento!');
						continuar = false;
					} else {
						palabraFormada = '';
						filaActual++;
					}
				}
			}
			peticion.send();
		}
	} else if (evento.key == 'Backspace' && continuar) {
		palabraFormada = palabraFormada.substring(0, palabraFormada.length - 1);
	}

	var celdas = celdasTotales.slice(5 * filaActual, 5 * (filaActual + 1));
	for (var i = 0; i < celdas.length; i++)
		celdas[i].innerHTML = palabraFormada.charAt(i);
});

document.getElementById('reiniciar').addEventListener('click', function() {
	for (var i = 0; i < celdasTotales.length; i++) {
		celdasTotales[i].innerHTML = '';
		celdasTotales[i].style.backgroundColor = 'lightgrey';
	}
	palabraFormada = '';
	filaActual = 0;
	nuevaPalabra();
	continuar = true;
});