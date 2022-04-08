var palabraSecreta = '';
var filaActual = 0;
var palabraFormada = '';
var continuar = true;

nuevaPalabra();

function nuevaPalabra() {
	var peticion = new XMLHttpRequest();
	peticion.open('GET', 'wordle/getNuevaPalabra');
	peticion.onload = function() {
		console.log(palabraSecreta = this.responseText);
	}
	peticion.send();
}

document.addEventListener('keyup', function(evento) {
	if (evento.key.length === 1 && evento.key.match(/[a-zA-ZñÑ]/) && continuar) {
		palabraFormada += evento.key.toUpperCase();

		var celdas = Array.from(document.querySelectorAll('td')).slice(5 * filaActual, 5 * (filaActual + 1));
		for (var i = 0; i < celdas.length; i++)
			celdas[i].innerHTML = palabraFormada.charAt(i);

		if (palabraFormada.length == 5) {
			for (var i = 0; i < celdas.length; i++)
				if (palabraSecreta.charAt(i) == palabraFormada.charAt(i))
					celdas[i].style.background = '#6aaa64'; // Verde
				else if (palabraSecreta.includes(palabraFormada.charAt(i)))
					celdas[i].style.background = '#c9b458'; // Amarillo
				else
					celdas[i].style.background = '#787c7e'; // Gris

			if (palabraSecreta == palabraFormada)
				continuar = false;
			else {
				palabraFormada = '';
				filaActual++;
			}
		}
	}
});

document.getElementById('reiniciar').addEventListener('click', function() {
	var celdasTotales = document.querySelectorAll('td');
	for (var i = 0; i < celdasTotales.length; i++) {
		celdasTotales[i].innerHTML = '';
		celdasTotales[i].style.backgroundColor = 'lightgrey';
	}
	palabraFormada = '';
	filaActual = 0;
	nuevaPalabra();
	continuar = true;
});