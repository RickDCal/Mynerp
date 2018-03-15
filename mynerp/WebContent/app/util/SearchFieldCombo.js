Ext.define('Mynerp.util.SearchFieldCombo', {
    extend: 'Ext.form.field.ComboBox',

    alias: 'widget.mysearchfieldcombo',

    listeners: {
        change: 'onSearchClick',
        scope: 'this'
    },

    hasSearch : false,
    filterName : null, //embaixo vai mudar este nome para o nome que tiver sido declarado no campo
    storeName: null,
    /**OBRIGATÓRIO .... container que possui a store ou o nome da store caso 
     **queira uma nova instância de uma store que tem o xtype indicado 
     **/

    initComponent: function() {
        var me = this,
            componenteDaStore = me.up(me.storeName),
            value = me.getValue(),
            storeFiltrar,
            proxy;
        
        //se o nome do parametro não for definido vai pegar o nome do campo de busca
        if (me.filterName == null) {
            me.filterName = me.getName();
        } 

        if (componenteDaStore) { //aqui estava grid
            storeFitrar = componenteDaStore.getStore();
        }

        me.callParent(arguments); 

        if (value == 1 || value == 2) {
            me.onSearchClick();
        } else {
            me.onClearClick();
        }        

    },

    onClearClick : function(){
        var me = this,
            activeFilter = me.activeFilter,
            componenteDaStore = me.up(me.storeName),
            storeFiltrar;

        if (componenteDaStore) {
                storeFiltrar = componenteDaStore.getStore();
            }

        if (activeFilter) {
            me.reset();//me.setValue('');
            storeFiltrar.getFilters().remove(activeFilter);
            me.activeFilter = null;
            me.updateLayout();
        }
    },

    onSearchClick : function(){
        var me = this,
            value = me.getValue(),
            componenteDaStore = me.up(me.storeName),
            storeFiltrar;

        if (componenteDaStore) {
                storeFiltrar = componenteDaStore.getStore();
            }

        if (value.length > 0) {
            me.activeFilter = new Ext.util.Filter({
                property: me.filterName,
                value: value
            });
            storeFiltrar.setRemoteFilter(true);
            storeFiltrar.getFilters().add(me.activeFilter);            
            me.updateLayout();

        } else { //caso mande buscar string vazia eu limpo este filtro se ele estiver ativo
            me.onClearClick();
        }
    }
});