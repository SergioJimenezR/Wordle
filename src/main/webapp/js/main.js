var palabraSecreta = 'REINA';
var filaActual = 0;

document.getElementById('input').addEventListener('keyup', function() {
	input.value = input.value.toUpperCase();
	var celdas = Array.from(document.querySelectorAll('td')).slice(5 * filaActual, 5 * (filaActual + 1));
	for (var i = 0; i < celdas.length; i++)
		celdas[i].innerHTML = input.value.charAt(i);
});

document.getElementById('validar').addEventListener('click', function() {
	if (input.value.length == 5) {
		var celdas = Array.from(document.querySelectorAll('td')).slice(5 * filaActual, 5 * (filaActual + 1));
		for (var i = 0; i < celdas.length; i++)
			if (palabraSecreta.charAt(i) == input.value.charAt(i))
				celdas[i].style.background = '#6aaa64'; // Verde
			else if (palabraSecreta.includes(input.value.charAt(i)))
				celdas[i].style.background = '#c9b458'; // Amarillo
			else
				celdas[i].style.background = '#787c7e'; // Gris
		input.value = '';
		filaActual++;
	}
});

document.getElementById('reiniciar').addEventListener('click', function() {
	var celdasTotales = document.querySelectorAll('td');
	for (var i = 0; i < celdasTotales.length; i++) {
		celdasTotales[i].innerHTML = '';
		celdasTotales[i].style.backgroundColor = 'lightgrey';
	}
	input.value = '';
	filaActual = 0;
	palabraSecreta = 'REINA';
});