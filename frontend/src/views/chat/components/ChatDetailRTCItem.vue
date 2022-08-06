<template>
<div v-if="streamManager">
	<ov-video :stream-manager="streamManager"/>
	<div><p>{{ clientData }}</p></div>
</div>
</template>

<script>
import OvVideo from './OvVideo';
export default {
  name: 'UserVideo',
<<<<<<< HEAD

	components: {
		OvVideo,
	},

=======
  components: {
	OvVideo,
   },
>>>>>>> 58e7f32a79cf96b06e081ff2fececfeb6a1aa4e5
	props: {
		streamManager: Object,
	},
	created () {
		console.log(this.streamManager)
	},
	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
	},
}
</script>

<style>

</style>