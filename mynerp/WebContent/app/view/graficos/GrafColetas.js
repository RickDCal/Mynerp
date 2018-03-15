Ext.define('Mynerp.view.graficos.GrafColetas', {
    extend: 'Ext.chart.CartesianChart',
    alias: 'widget.column-basic',

    //bind: '{relat-coletas}',

    store: {
             type: 'relat-coletas'
    },

    insetPadding: 40,
    interactions: 'itemhighlight',
    flipXY: true,

    axes: [{
        type: 'numeric',
        position: 'bottom',
        fields: ['valor'],
        label: {
            renderer: Ext.util.Format.numberRenderer('0,0')
        },
        title: 'Total Sales',
        grid: true,
        minimum: 0
    }, {
        type: 'category',
        position: 'left',
        fields: ['mes'],
        title: 'Film Category'
    }],
    series: [{
        type: 'bar',
        highlight: {
            strokeStyle: 'black',
            //fillStyle: '#57cbd1',
            radius: 5
        },
        tooltip: {
            trackMouse: true,
            style: 'background: #fff',
            //width: 140,
            //height: 28,
            renderer: function(storeItem, item, attr) {
                this.setTitle(storeItem.get('mes') + ': ' + storeItem.get('valor') + ' $');
            }
        },
        label: {
            display: 'insideEnd',
            'text-anchor': 'middle',
            field: 'valor',
            renderer: Ext.util.Format.numberRenderer('0'),
            orientation: 'horizontal',
            color: '#333',
            contrast: true
        },
        xField: 'mes',
        yField: 'valor'
    }]
});

// Ext.define('Mynerp.view.graficos.GrafColetas', {
//     extend: 'Ext.Panel',
//     xtype: 'column-basic',
//     controller: 'chart-column-basic',

//     width: 650,
//     height: 500,

//     // tbar: [
//     //     '->',
//     //     {
//     //         text: 'Preview',
//     //         platformConfig: {
//     //             desktop: {
//     //                 text: 'Download'
//     //             }
//     //         },
//     //         handler: 'onDownload'
//     //     },
//     //     {
//     //         text: 'Reload Data',
//     //         handler: 'onReloadData'
//     //     }
//     // ],

//     items: {
//         xtype: 'cartesian',
//         reference: 'chart',
//         store: {
//             type: 'relat-coletas'
//         },
//         insetPadding: {
//             top: 40,
//             bottom: 40,
//             left: 20,
//             right: 40
//         },
//         interactions: 'itemhighlight',
//         axes: [{
//             type: 'numeric',
//             position: 'left',
//             minimum: 10,
//             titleMargin: 20,
//             title: {
//                 text: 'Temperature in °F'
//             },
//             listeners: {
//                 rangechange: 'onAxisRangeChange'
//             }
//         }, {
//             type: 'category',
//             position: 'bottom'
//         }],
//         animation: Ext.isIE8 ? false : {
//             easing: 'backOut',
//             duration: 500
//         },
//         series: {
//             type: 'bar',
//             xField: 'mes',
//             yField: 'valor',
//             style: {
//                 minGapWidth: 20
//             },
//             highlight: {
//                 strokeStyle: 'black',
//                 fillStyle: 'gold',
//                 lineDash: [5, 3]
//             },
//             label: {
//                 field: 'mes',
//                 display: 'insideEnd',
//                 renderer: function (value) {
//                     return value.toFixed(1);
//                 }
//             }
//         },
//         sprites: {
//             type: 'text',
//             text: 'Redwood City Climate Data',
//             fontSize: 22,
//             width: 100,
//             height: 30,
//             x: 40, // the sprite x position
//             y: 20  // the sprite y position
//         }
//     }

// });