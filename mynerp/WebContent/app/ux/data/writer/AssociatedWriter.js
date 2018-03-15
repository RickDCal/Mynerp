Ext.define('Mynerp.ux.data.writer.AssociatedWriter', {
    extend: 'Ext.data.writer.Json',
    alias: 'writer.associatedjson',

    constructor: function(config) {
        this.callParent(arguments);
    },

   // original
    getRecordData: function (record, operation) { 
        record.data = this.callParent(arguments);
        Ext.apply(record.data, record.getAssociatedData());
        return record.data;
    }

     //  getRecordData: function (record, operation) {
     //     record.data = this.callParent(arguments);
     //     if (record.writeStructuredData) {
     //        Ext.apply(record.data, record.getWriteData()); //apply faz um merge das propriedades do segundo jogando no primeiro
     //     }else{
     //        Ext.apply(record.data, record.getAssociatedData()); // getAssociatedData por algum motivo não está retornando nenhuma associação de parcelas ou qualquer outra
     //     }
     //     return record.data;
     // }

});