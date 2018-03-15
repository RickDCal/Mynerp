//Ext.define('Ext.ux.form.SearchField', { esta classe foi copiada e colada aqui para modificar
Ext.define('Mynerp.util.SearchField', {
    extend: 'Ext.form.field.Text',

    alias: 'widget.mysearchfield',

    triggers: {
        clear: {
            weight: 0,
            cls: Ext.baseCSSPrefix + 'form-clear-trigger',
            hidden: true,
            handler: 'onClearClick',
            scope: 'this'
        },
        search: {
            weight: 1,
            cls: Ext.baseCSSPrefix + 'form-search-trigger',
            handler: 'onSearchClick',
            scope: 'this'
        }
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
            store, //= me.store,
            proxy;
        
        //se o nome do parametro não for definido vai pegar o nome do campo de busca
        if (me.filterName == null) {
            me.filterName = me.getName();
        } 

        if (componenteDaStore) { //aqui estava grid
            store = componenteDaStore.getStore();
        }

        me.callParent(arguments);
        me.on('specialkey', function(f, e){
            if (e.getKey() == e.ENTER) {
                me.onSearchClick();
            }
        });
        
        if (!store || !store.isStore) {
            store = me.storeName = Ext.data.StoreManager.lookup(store); 
            /* se não houver store ou ela não for válida vai pegar o nome que foi passado e vai
            tentar instancia uma store nova, por isso o atributo serve para receber xtype do 
            form que tem a store ou o xtype da propria store.
            **tem que passar o atributo lá no docked item senão a store será indefinida aqui. 
            */
        }
        // We're going to use filtering
        store.setRemoteFilter(true); // para forçar, mas isso pode já estar na store
        proxy = store.getProxy();
    },

    onClearClick : function(){
        var me = this,
            activeFilter = me.activeFilter,
            componenteDaStore = me.up(me.storeName),
            store;

        if (componenteDaStore) {
                store = componenteDaStore.getStore();
            }

        if (activeFilter) {
            me.setValue('');
            store.getFilters().remove(activeFilter);
            me.activeFilter = null;
            me.getTrigger('clear').hide();
            me.updateLayout();
        }
    },

    onSearchClick : function(){
        var me = this,
            value = me.getValue(),
            componenteDaStore = me.up(me.storeName),
            store;

        if (componenteDaStore) {
                store = componenteDaStore.getStore();
            }


        if (value.length > 0) {
            // Param name is ignored here since we use custom encoding in the proxy.
            // id is used by the Store to replace any previous filter
            me.activeFilter = new Ext.util.Filter({
                property: me.filterName,
                value: value
            });
            store.setRemoteFilter(true);
            store.getFilters().add(me.activeFilter);            
            me.getTrigger('clear').show();
            me.updateLayout();

        } else { //caso mande buscar string vazia eu limpo este filtro se ele estiver ativo
            me.onClearClick();
        }
    }
});