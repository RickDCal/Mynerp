Ext.define('Mynerp.model.financeiro.Base', {
    extend:'Mynerp.model.Base',

    identifier: 'uuid',

    fields: [
        //{name: 'id', type: 'int', defaultValue: '0'} aqui qdo estava testando a criação de lista valor com enter
        {name: 'id'} // deixando string por causa do uuid
    ]

});