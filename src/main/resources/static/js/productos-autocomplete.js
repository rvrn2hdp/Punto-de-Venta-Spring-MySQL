
$(document).ready(() => {
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
                        };
                    }));
                }
            });
        },

        select: function (event, ui) {


            //crear una linea para la tabla de linea de ventas... 
            let linea = $("#ventaItems").html();

            //decontruir la cadena:
            let producto = ui.item.label;
            let descripcion = producto.split('-')[0];
            let precio = producto.split('-')[1];
            precio = precio.split('$')[1];
            let id = ui.item.value;

            console.log('Producto seleccionado:  $(producto)');

            linea = linea.replace(/{ID}/g, id);
            linea = linea.replace(/{DESCRIPCION}/g, descripcion);
            linea = linea.replace(/{PRECIO}/g, precio);

            $("#tabla tbody").append(linea);

            lineasUtil.calcularSubtotal(id, precio, 1);

            return false;
        }
    });
});

const lineasUtil = {

    incrementarCantidad: (id) => {
        let cantidad = parseInt($(`#cantidad_${id}`).val());
        $(`#cantidad_${id}`).val(cantidad++);
        this.calcularSubtotal(id, );
    },

    calcularSubtotal: (id, precio, cantidad) => {
        $(`#subtotal_${id}`).html((parseFloat(precio) * parseInt(cantidad)).toFixed(2));
    },

    esRepetido: (id) => {
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
        $(`span[id^="total`).each(
                function () {
                    total += parseFloat($(this).html());
                }
        );
        $("#total").html("$" + parseFloat(total).toFixed(2));
    }
};
