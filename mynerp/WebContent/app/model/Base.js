Ext.define('Mynerp.model.Base', {
    extend: 'Ext.data.Model',

    /*propriedades de Id foram tiradas daqui e colocadas nos baseModel de cada pasta
    idProperty: 'id',
    clientIdProperty: 'clientId',
    identifier: 'uuid',
    */
    idProperty: 'id', // o Id está ficando string, em caso de invalid id returned, pode ser necessário um parseInt() para converter valor string em inteiro para garregar um model pelo Id.
    clientIdProperty: 'clientIdProperty', //tem que voltar isso nas consultas de inclusao e edição para que não dê o problema de ignoring server record.

    requires: [
        'Mynerp.util.Util',
        'Mynerp.ux.data.writer.AssociatedWriter'
    ],

    schema: {
        namespace: 'Mynerp.model',
        proxy: {
            type: 'ajax',
            api :{
                read: '{entityName:lowercase}Servlet?action=2',//'{prefix}/{entityName: lowercase}/list.php',//
                create: '{entityName:lowercase}Servlet?action=1',////create: '{prefix}/{entityName: lowercase}/create.php'//
                update: '{entityName:lowercase}Servlet?action=3',//update: '{prefix}/{entityName: lowercase}/update.php'//
                destroy: '{entityName:lowercase}Servlet?action=4'//destroy: '{prefix}/{entityName: lowercase}/destroy.php'//
            },
            reader: {
                type: 'json',
                rootProperty: 'data'
            },
            writer: {
                //type: 'associatedjson', //estava assim e estou tentando fazer enviar os dados aninhados
                type: 'json',
                allDataOptions: { // isso substituiu associatedJson
                    associated: true
                },
                writeAllFields: true,
                encode: true,
                rootProperty: 'data',
                allowSingle: false,
                dateFormat: "Y-m-d H:i:s"
            },
            listeners: {
                exception: function(proxy, response, operation){

                    result = Mynerp.util.Util.decodeJSON(response.responseText); 
                    if (result.mensagemRetorno) {
                        Mynerp.util.Util.showErrorMsg(result.mensagemRetorno);
                    } else {
                        Mynerp.util.Util.showErrorMsg(response.responseText);
                    }                   
                }
            },
            filterParam: 'filter' // Parameter name to send filtering information in-- era query
        }

    }
});