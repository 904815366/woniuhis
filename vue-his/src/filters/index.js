import moment from 'moment' 
import Vue from 'vue'

Vue.filter('dateconverter',function(value,format) {
   
   

    return moment(value).subtract(1, 'months').format('YYYY-MM-DD HH:mm:ss')

})

// Vue.filter('dateconverter',function(value) {
//     return moment(datetime).utcOffset(480).format('YYYY-MM-DD HH:mm:ss');
// })