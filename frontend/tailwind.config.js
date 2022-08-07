/** @type {import('tailwindcss').Config} */
const withMT = require("@material-tailwind/html/utils/withMT");
// const colors = require('tailwindcss/colors')


module.exports = {
  content: [
  './index.html',
  './src/components/NavBar.vue',
  './src/views/modal/components/LoginModal.vue',
  './src/views/modal/components/ChatRoomCreateModal.vue',
  './src/views/communication/components/CommunicationList.vue',
  './src/views/chat/components/ChatMainCardListItem.vue',
  './src/views/chat/components/ChatMainCardList.vue',
  './src/views/communication/components/CommunicationList.vue',
  './src/views/chat/ChatMainView.vue'],
  theme: {
    extend: {
      colors: {
        'newBlue1': '#2657a7',
        'newPink1': '#f1c4d9',
        'newBlack': '#1a1e23',
        'newOrange': '#f16b48',
        'newPink2': '#f38e7b',
        'newBlue2': '#6382bd',
        'newGrey1': '#c9d4e7',
        'newGrey2': '#575b5f',
        'newPink3': '#f7bfb4',
      },
      fontFamily: {
        // 'sans': ['Proxima Nova', ...defaultTheme.fontFamily.sans],
      },
    },
  },
  plugins: [require("daisyui")  ],
}
