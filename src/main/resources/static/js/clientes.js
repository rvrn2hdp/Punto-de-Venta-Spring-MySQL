$(document).ready(function () {
    
    $('#tablaClientes').DataTable({
        lengthMenu: [3, 6, 9, 12],
        language: {
            "search": "Buscar: ",
            "lengthMenu": "Mostrar _MENU_ registros",
            "info": "Mostrando _START_ a _END_ de _TOTAL_ registros",
            "paginate": {
                "previous": "Anterior",
                "next": "Siguiente"
            }
        }
    });
});


