try {
    /*
        ==============================
        |    @Options Charts Script   |
        ==============================
    */

    /*
        ======================================
            Visitor Statistics | Options
        ======================================
    */

    // Total Visits






//发送请求
    $.ajax({
        type: "POST",
        url: "/find_data",
        contentType: 'application/json',

        success: function (response) {

            if (response.code==200){

                var arr_1 = response.demand1.split(',');
                var arr_2 = response.demand2.split(',');

                var arr_3 = response.demand3.split(',');
                var arr_4 = response.demand4.split(',');
                var arr_5 = response.demand5.split(',');
                var arr_6 = response.demand6.split(',');
                var arr_7 = response.demand7.split(',');
                var arr_5_100 = []
                var arr_1_100 = []

                for (i in arr_5){


                    arr_5_100.push(arr_5[i]*100)

                }

                for (i in arr_1){


                    arr_1_100.push(arr_1[i]*100)

                }



                var arr_8 = response.demand8.split(',');

                var arr_9 = response.demand9.split(',');
                var arr_10 = response.demand10.split(',');
                var caldate = response.calDate.split(',');

                $("#calDate").text(caldate)
                $("#chin").text(arr_10)
                var spark1 = {
                    chart: {
                        id: 'total-users',
                        group: 'sparks1',
                        type: 'line',
                        height: 80,
                        sparkline: {
                            enabled: true
                        },
                        dropShadow: {
                            enabled: true,
                            top: 3,
                            left: 1,
                            blur: 3,
                            color: '#009688',
                            opacity: 0.6,
                        }
                    },
                    series: [{
                        data: arr_7
                    }],
                    stroke: {
                        curve: 'smooth',
                        width: 2,
                    },
                    markers: {
                        size: 0
                    },
                    grid: {
                        padding: {
                            top: 35,
                            bottom: 0,
                            left: 40
                        }
                    },
                    colors: ['#009688'],
                    tooltip: {
                        x: {
                            show: false
                        },
                        y: {
                            title: {
                                formatter: function formatter(val) {
                                    return '';
                                }
                            }
                        }
                    },
                    responsive: [{
                        breakpoint: 1351,
                        options: {
                            chart: {
                                height: 95,
                            },
                            grid: {
                                padding: {
                                    top: 35,
                                    bottom: 0,
                                    left: 0
                                }
                            },
                        },
                    },
                        {
                            breakpoint: 1200,
                            options: {
                                chart: {
                                    height: 80,
                                },
                                grid: {
                                    padding: {
                                        top: 35,
                                        bottom: 0,
                                        left: 40
                                    }
                                },
                            },
                        },
                        {
                            breakpoint: 576,
                            options: {
                                chart: {
                                    height: 95,
                                },
                                grid: {
                                    padding: {
                                        top: 35,
                                        bottom: 0,
                                        left: 0
                                    }
                                },
                            },
                        }
                    ]
                }

                // Paid Visits

                var spark2 = {
                    chart: {
                        id: 'unique-visits',
                        group: 'sparks2',
                        type: 'line',
                        height: 80,
                        sparkline: {
                            enabled: true
                        },
                        dropShadow: {
                            enabled: true,
                            top: 1,
                            left: 1,
                            blur: 2,
                            color: '#e2a03f',
                            opacity: 0.2,
                        }
                    },
                    series: [{
                        data:  arr_8
                    }],
                    stroke: {
                        curve: 'smooth',
                        width: 2,
                    },
                    markers: {
                        size: 0
                    },
                    grid: {
                        padding: {
                            top: 35,
                            bottom: 0,
                            left: 40
                        }
                    },
                    colors: ['#e2a03f'],
                    tooltip: {
                        x: {
                            show: false
                        },
                        y: {
                            title: {
                                formatter: function formatter(val) {
                                    return '';
                                }
                            }
                        }
                    },
                    responsive: [{
                        breakpoint: 1351,
                        options: {
                            chart: {
                                height: 95,
                            },
                            grid: {
                                padding: {
                                    top: 35,
                                    bottom: 0,
                                    left: 0
                                }
                            },
                        },
                    },
                        {
                            breakpoint: 1200,
                            options: {
                                chart: {
                                    height: 80,
                                },
                                grid: {
                                    padding: {
                                        top: 35,
                                        bottom: 0,
                                        left: 40
                                    }
                                },
                            },
                        },
                        {
                            breakpoint: 576,
                            options: {
                                chart: {
                                    height: 95,
                                },
                                grid: {
                                    padding: {
                                        top: 35,
                                        bottom: 0,
                                        left: 0
                                    }
                                },
                            },
                        }

                    ]
                }


                var spark3 = {
                    chart: {
                        id: 'total-users1',
                        group: 'sparks3',
                        type: 'line',
                        height: 80,
                        sparkline: {
                            enabled: true
                        },
                        dropShadow: {
                            enabled: true,
                            top: 3,
                            left: 1,
                            blur: 3,
                            color: '#723596',
                            opacity: 0.6,
                        }
                    },
                    series: [{
                        data: arr_9
                    }],
                    stroke: {
                        curve: 'smooth',
                        width: 2,
                    },
                    markers: {
                        size: 0
                    },
                    grid: {
                        padding: {
                            top: 35,
                            bottom: 0,
                            left: 40
                        }
                    },
                    colors: ['#723596'],
                    tooltip: {
                        x: {
                            show: false
                        },
                        y: {
                            title: {
                                formatter: function formatter(val) {
                                    return '';
                                }
                            }
                        }
                    },
                    responsive: [{
                        breakpoint: 1351,
                        options: {
                            chart: {
                                height: 95,
                            },
                            grid: {
                                padding: {
                                    top: 35,
                                    bottom: 0,
                                    left: 0
                                }
                            },
                        },
                    },
                        {
                            breakpoint: 1200,
                            options: {
                                chart: {
                                    height: 80,
                                },
                                grid: {
                                    padding: {
                                        top: 35,
                                        bottom: 0,
                                        left: 40
                                    }
                                },
                            },
                        },
                        {
                            breakpoint: 576,
                            options: {
                                chart: {
                                    height: 95,
                                },
                                grid: {
                                    padding: {
                                        top: 35,
                                        bottom: 0,
                                        left: 0
                                    }
                                },
                            },
                        }
                    ]
                }
                /*
                    ===================================
                        Unique Visitors | Options
                    ===================================
                */

                var d_1options1 = {
                    chart: {
                        height: 350,
                        type: 'bar',
                        toolbar: {
                            show: false,
                        },
                        dropShadow: {
                            enabled: true,
                            top: 1,
                            left: 1,
                            blur: 2,
                            color: '#acb0c3',
                            opacity: 0.7,
                        }
                    },
                    colors: ['#5c1ac3', '#ffbb44'],
                    plotOptions: {
                        bar: {
                            horizontal: false,
                            columnWidth: '55%',
                            endingShape: 'rounded'
                        },
                    },
                    dataLabels: {
                        enabled: false
                    },
                    legend: {
                        position: 'bottom',
                        horizontalAlign: 'center',
                        fontSize: '14px',
                        markers: {
                            width: 10,
                            height: 10,
                        },
                        itemMargin: {
                            horizontal: 0,
                            vertical: 8
                        }
                    },
                    stroke: {
                        show: true,
                        width: 2,
                        colors: ['transparent']
                    },
                    series: [{
                        name: '均值',
                        data: arr_6
                    }],
                    xaxis: {
                        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep','Oct','Nov','Dec'],
                    },
                    fill: {
                        type: 'gradient',
                        gradient: {
                            shade: 'light',
                            type: 'vertical',
                            shadeIntensity: 0.3,
                            inverseColors: false,
                            opacityFrom: 1,
                            opacityTo: 0.8,
                            stops: [0, 5]
                        }
                    },
                    tooltip: {
                        y: {
                            formatter: function (val) {
                                return val
                            }
                        }
                    }
                }


                var d_1options6 = {
                    chart: {
                        height: 350,
                        type: 'bar',
                        toolbar: {
                            show: false,
                        },
                        dropShadow: {
                            enabled: true,
                            top: 1,
                            left: 1,
                            blur: 2,
                            color: '#acb0c3',
                            opacity: 0.7,
                        }
                    },
                    colors: ['#009688'],
                    plotOptions: {
                        bar: {
                            horizontal: false,
                            columnWidth: '30%',
                            endingShape: 'rounded'
                        },
                    },
                    dataLabels: {
                        enabled: false
                    },
                    legend: {
                        position: 'bottom',
                        horizontalAlign: 'center',
                        fontSize: '14px',
                        markers: {
                            width: 10,
                            height: 10,
                        },
                        itemMargin: {
                            horizontal: 0,
                            vertical: 8
                        }
                    },
                    stroke: {
                        show: true,
                        width: 2,
                        colors: ['transparent']
                    },
                    series: [{
                        name: '占比',
                        data: arr_3
                    }],
                    xaxis: {
                        categories: ['动作', '爱情', '喜剧', '动画', '冒险', '歌舞'],
                    },
                    fill: {
                        type: 'gradient',
                        gradient: {
                            shade: 'light',
                            type: 'vertical',
                            shadeIntensity: 0.3,
                            inverseColors: false,
                            opacityFrom: 1,
                            opacityTo: 0.8,
                            stops: [0, 100]
                        }
                    },
                    tooltip: {
                        y: {
                            formatter: function (val) {
                                return val
                            }
                        }
                    }
                }


                /*
                    ======================================
                        Visitor Statistics | Script
                    ======================================
                */

                // Total Visits
                d_1C_1 = new ApexCharts(document.querySelector("#total-users"), spark1);
                d_1C_1.render();

                // Paid Visits
                d_1C_2 = new ApexCharts(document.querySelector("#paid-visits"), spark2);
                d_1C_2.render();

                d_1C_8 = new ApexCharts(document.querySelector("#total-users1"), spark3);
                d_1C_8.render();
                /*
                    ===================================
                        Unique Visitors | Script
                    ===================================
                */

                var d_1C_3 = new ApexCharts(
                    document.querySelector("#uniqueVisits"),
                    d_1options1
                );
                d_1C_3.render();

                var d_1C_6 = new ApexCharts(
                    document.querySelector("#uniqueVisits1"),
                    d_1options6
                );
                d_1C_6.render();




                var options = {
                    chart: {
                        type: 'donut',
                        width: 380
                    },
                    colors: ['#5c1ac3', '#e2a03f', '#e7515a', '#e2a03f'],
                    dataLabels: {
                        enabled: false
                    },
                    legend: {
                        position: 'bottom',
                        horizontalAlign: 'center',
                        fontSize: '14px',
                        markers: {
                            width: 10,
                            height: 10,
                        },
                        itemMargin: {
                            horizontal: 0,
                            vertical: 8
                        }
                    },
                    plotOptions: {
                        pie: {
                            donut: {
                                size: '65%',
                                background: 'transparent',
                                labels: {
                                    show: true,
                                    name: {
                                        show: true,
                                        fontSize: '29px',
                                        fontFamily: 'Nunito, sans-serif',
                                        color: undefined,
                                        offsetY: -10
                                    },
                                    value: {
                                        show: true,
                                        fontSize: '26px',
                                        fontFamily: 'Nunito, sans-serif',
                                        color: '20',
                                        offsetY: 16,
                                        formatter: function (val) {
                                            return val
                                        }
                                    },
                                    total: {
                                        show: true,
                                        showAlways: true,
                                        label: '总值',
                                        color: '#888ea8',
                                        formatter: function (w) {
                                            return w.globals.seriesTotals.reduce( function(a, b) {
                                                return a + b
                                            }, 0)
                                        }
                                    }
                                }
                            }
                        }
                    },
                    stroke: {
                        show: true,
                        width: 25,
                    },
                    series: arr_5_100,
                    labels: ['四星', '二星'],
                    responsive: [{
                        breakpoint: 1599,
                        options: {
                            chart: {
                                width: '350px',
                                height: '400px'
                            },
                            legend: {
                                position: 'bottom'
                            }
                        },

                        breakpoint: 1439,
                        options: {
                            chart: {
                                width: '250px',
                                height: '390px'
                            },
                            legend: {
                                position: 'bottom'
                            },
                            plotOptions: {
                                pie: {
                                    donut: {
                                        size: '65%',
                                    }
                                }
                            }
                        },
                    }]
                }

                /*
                    =================================
                        Sales By Category | Render
                    =================================
                */
                var chart = new ApexCharts(
                    document.querySelector("#chart-2"),
                    options
                );

                chart.render();





                var options1 = {
                    chart: {
                        type: 'donut',
                        width: 380
                    },
                    colors: ['#5fc32e', '#414c91'],
                    dataLabels: {
                        enabled: false
                    },
                    legend: {
                        position: 'bottom',
                        horizontalAlign: 'center',
                        fontSize: '14px',
                        markers: {
                            width: 10,
                            height: 10,
                        },
                        itemMargin: {
                            horizontal: 0,
                            vertical: 8
                        }
                    },
                    plotOptions: {
                        pie: {
                            donut: {
                                size: '65%',
                                background: 'transparent',
                                labels: {
                                    show: true,
                                    name: {
                                        show: true,
                                        fontSize: '29px',
                                        fontFamily: 'Nunito, sans-serif',
                                        color: undefined,
                                        offsetY: -10
                                    },
                                    value: {
                                        show: true,
                                        fontSize: '26px',
                                        fontFamily: 'Nunito, sans-serif',
                                        color: '20',
                                        offsetY: 16,
                                        formatter: function (val) {
                                            return val
                                        }
                                    },
                                    total: {
                                        show: true,
                                        showAlways: true,
                                        label: '上映总数',
                                        color: '#888ea8',
                                        formatter: function (w) {
                                            return w.globals.seriesTotals.reduce( function(a, b) {
                                                return a + b
                                            }, 0)
                                        }
                                    }
                                }
                            }
                        }
                    },
                    stroke: {
                        show: true,
                        width: 25,
                    },
                    series: arr_1_100,
                    labels: ['国内', '国外'],
                    responsive: [{
                        breakpoint: 1599,
                        options: {
                            chart: {
                                width: '350px',
                                height: '400px'
                            },
                            legend: {
                                position: 'bottom'
                            }
                        },

                        breakpoint: 1439,
                        options: {
                            chart: {
                                width: '250px',
                                height: '390px'
                            },
                            legend: {
                                position: 'bottom'
                            },
                            plotOptions: {
                                pie: {
                                    donut: {
                                        size: '65%',
                                    }
                                }
                            }
                        },
                    }]
                }

                var chart = new ApexCharts(
                    document.querySelector("#chart-3"),
                    options1
                );

                chart.render();

















                /*
                    =============================================
                        Perfect Scrollbar | Recent Activities
                    =============================================
                */







            }else {
                alert(response.message)
                window.location.replace("http://127.0.0.1:8080/calculate");

            }


        },
        dataType: "json"
    });



} catch(e) {
  // statements
  console.log(e);
}


