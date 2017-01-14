'use strict';
import React, { Component } from 'react';
import {
  StyleSheet,
  TextInput,
  TouchableHighlight,
  AsyncStorage,
  Text,
  View,
    ScrollView,
    Alert
} from 'react-native';

import Button from 'react-native-button';

var update = require('react-addons-update');
var _ = require('underscore-node');

var alertMessage = 'Place cannot be empty!';

class PlacesList extends Component {

	constructor(){
		super();
		this.edit = this.edit.bind(this);
		this.state = {
			places: [
                {country: 'Romania', city: 'Sibiu', rating: 9},
                {country: 'Romania', city: 'Brasov', rating: 8},
                {country: 'France', city: 'Paris', rating: 10}
                ],
			newPlace: ''
		}
	}

  	navigate(routeName, data, index) {
      		this.props.navigator.push({
        		name: routeName,
        		data: data,
				callback: this.edit,
				placeIndex: index
        	});
    	}

	redirect(routeName, accessToken){
		this.props.navigator.push({
			name: routeName
		});
	}

    savePlace() {
        var placesList = this.state.places;
        if (this.state.newPlace == "") {
            Alert.alert(
                'Error',
                alertMessage,
            )
        }
        else {
            var newPlace = this.state.newPlace.split(" ");
            placesList.push({country: newPlace[0], city: newPlace[1], rating: newPlace[2]});
            this.setState({places: placesList});

            try {
                AsyncStorage.setItem('@placesList:key', JSON.stringify(placesList)).then((data) => {

                });

            } catch (error) {
                // Error saving data
            }

            this.setState({newPlace: ''})
        }
    }

    delete(index) {
        var placesList = this.state.places;
        var placesList_tmp = [];
        var i = 0;
        placesList.map((place) => {
            if (index != i) {
                placesList_tmp.push(place);
            }
            i += 1;
        });

        delete placesList[index];
        this.setState({places: placesList});

        try {
            AsyncStorage.setItem('@placesList:key', JSON.stringify(placesList_tmp)).then((data) => {

            });

        } catch (error) {
            // Error saving data
        }
    }


    edit(placeCountry, placeCity, placeRating, index) {
	    console.log("**** country" + placeCountry);
	    console.log("**** city" + placeCity);
	    console.log("**** rating" + placeRating);
	    console.log("**** index");
	    console.log(index);
        var placesListcopy = this.state.places;
        var intIndex = index.valueOf();

        var newPlace = _.extend({}, placesListcopy[index], { country: placeCountry, city: placeCity, rating: placeRating });
        console.log("**** New place");
        console.log(newPlace);
        var objA = {};
        objA[intIndex] = newPlace;
        var newP = _.extend([], placesListcopy, objA);
        console.log("***** New list");
        console.log(newP);
        this.setState({places: newP});
        console.log(this.state);

        try {
            AsyncStorage.setItem('@placesList:key', JSON.stringify(newP)).then((data) => {

            });

        } catch (error) {
            // Error saving data
        }
    }

    componentWillMount() {

        try {
            AsyncStorage.getItem('@placesList:key').then((data) => {
                if (data) {
                    this.setState({places: JSON.parse(data)});
                }
            });
        } catch (error) {

        }
    }

	render() {

		const l = this.state.places.map((data, index) => {
			return (
				<View key={index}>
					<Button onPress={ this.navigate.bind(this, 'editPlace', data, index) }>
					{data.country + ' ' + data.city + ' ' + data.rating}
					</Button>

                    <Button onPress={ this.delete.bind(this, index) }>Delete</Button>
				</View>
			)
		})

        return (
			<View style={styles.container}>
				<TextInput
					style={{width: 150,height: 40, borderColor: 'gray', borderWidth: 1}}
					onChangeText={(newPlace) => this.setState({newPlace})}
					value={this.state.newPlace}
				/>

				<Button onPress={ this.savePlace.bind(this) }>Add</Button>

				<Text style={styles.heading}>
					Places:
				</Text>
                <ScrollView>
                    {l}
                </ScrollView>
			</View>
        );
	}
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        backgroundColor: '#F5FCFF',
        padding: 10,
        paddingTop: 80
    },
    listContainer:{
        flex: 1,
        justifyContent: 'flex-start',
        backgroundColor: '#FFF',
        padding: 10,
        paddingTop: 80
    },
    input: {
        height: 50,
        marginTop: 10,
        padding: 4,
        fontSize: 18,
        borderWidth: 1,
        borderColor: '#48bbec'
    },
    button: {
        height: 50,
        backgroundColor: '#48BBEC',
        alignSelf: 'stretch',
        marginTop: 10,
        justifyContent: 'center'
    },
    buttonText: {
        fontSize: 22,
        color: '#FFF',
        alignSelf: 'center'
    },
    heading: {
        textAlign: 'center',
        fontSize: 30,
        marginBottom: 15,
        fontWeight: 'bold',
        // color: '#bcb696'
    }
});

export default PlacesList
