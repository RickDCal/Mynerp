/*fiz este override porque algumas datas estavam indo com o formato yyyy-mm-ddThh:mm:ss e a letra T me atrapalhava no gsonBuilder do java*/
Ext.define('Mynerp.overrides.json.encodeFormatoData', {
    override: 'Ext.JSON',

    /* ************original:************
    me.encodeDate = function(o) {
        return '"' + o.getFullYear() + "-" + pad(o.getMonth() + 1) + "-" + pad(o.getDate()) + "T" + pad(o.getHours()) + ":" + pad(o.getMinutes()) + ":" + pad(o.getSeconds()) + '"';
    };
    ************************************/

    encodeDate: function(d) {
    return Ext.Date.format(d, '"Y-m-d H:i:s"');
	}
	
});
