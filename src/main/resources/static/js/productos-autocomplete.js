
$(document).ready(function () {
    $("#buscar_producto").autocomplete({
        source: function (request, response) {
            $.ajax({
                url: `/ventas/buscar-productos/${request.term}`, //Forma antigua: "/ventas/buscar-productos/" + request.term
                dataType: "json",
                data: {
                    term: request.term
                },
                success: function (data) {
                    response($.map(data, function (item) {
                        return {
                            value: item.id,
                            label: `${item.descripcion} - $${item.precio}` //item.descripcion + " - $" + item.precio 
                        }
                    }));
                }
            });
        },
        select: function (event, ui) {

            //Crear una linea para la tabla de linea de ventas...
            let linea = $("#ventaItems").html();

            //Decostruir la cadena:
            let producto = ui.item.label;
            let descripcion = producto.split('-')[0];
            let precio = producto.split('-')[1];
            precio = precio.split('$')[1];
            let id = ui.item.value;

            console.log(`Producto seleccionado: ${producto}`);

            //Verficar si el producto es repetido:
            if (lineasUtil.esRepetido(id)) {
                lineasUtil.incrementarCantidad(id, precio);
                return false;
            }

            linea = linea.replace(/{ID}/g, id);
            linea = linea.replace(/{DESCRIPCION}/g, descripcion);
            linea = linea.replace(/{PRECIO}/g, precio);

            $("#tabla tbody").prepend(linea);

            lineasUtil.calcularSubtotal(id, precio, 1);


        }
    });
});

//Clase de utilidades
const lineasUtil = {

    incrementarCantidad: function(id, precio) {
        let cantidad = parseInt($(`#cantidad_${id}`).val());
        $(`#cantidad_${id}`).val(++cantidad);
        this.calcularSubtotal(id, precio, cantidad);
    }, 
    calcularSubtotal: function(id, precio, cantidad) {
        $(`#subtotal_${id}`).html((parseFloat(precio) * parseInt(cantidad)).toFixed(2));
        this.calcularTotal();
    },
    esRepetido: function (id) {
        let resultado = false;
        $('input[name="item_id[]"]').each(function () {
            if (parseInt(id) === parseInt($(this).val())) {
                resultado = true;
            }
        });
        return resultado;
    }, 
    calcularTotal: function () {
        let total = 0;
        $(`span[id^="subtotal_"]`).each(function() {
            total += parseFloat($(this).html());
        });
        $("#total").html("$" + parseFloat(total).toFixed(2));
    },
    eliminarLinea: function (id) {
        $(`#row_${id}`).remove();
        this.calcularTotal();
    }
};