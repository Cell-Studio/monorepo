package com.cellstudio.vgc.data.repositories

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.cellstudio.core.di.qualifiers.CellDispatchers
import com.cellstudio.core.di.qualifiers.Dispatcher
import com.cellstudio.vgc.data.MoveDetailsQuery
import com.cellstudio.vgc.data.MoveListQuery
import com.cellstudio.vgc.data.PokemonDetailQuery
import com.cellstudio.vgc.data.PokemonListQuery
import com.cellstudio.vgc.data.mapper.toMoveDamageType
import com.cellstudio.vgc.data.mapper.toMoveType
import com.cellstudio.vgc.domain.models.Ability
import com.cellstudio.vgc.domain.models.Move
import com.cellstudio.vgc.domain.models.MoveDamageType
import com.cellstudio.vgc.domain.models.MoveDetails
import com.cellstudio.vgc.domain.models.MoveType
import com.cellstudio.vgc.domain.models.Pokemon
import com.cellstudio.vgc.domain.models.PokemonDetails
import com.cellstudio.vgc.domain.models.PokemonImage
import com.cellstudio.vgc.domain.models.PokemonType
import com.cellstudio.vgc.domain.models.Stats
import com.cellstudio.vgc.domain.repositories.VgcRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class VgcRepositoryImpl @Inject constructor(
    @Dispatcher(CellDispatchers.DEFAULT)
    private val dispatcher: CoroutineDispatcher
): VgcRepository {
    private val apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl("https://beta.pokeapi.co/graphql/v1beta")
        .build()

    override suspend fun getPokemonList(name: String?): List<Pokemon> {
        return withContext(dispatcher) {
            val response = apolloClient.query(PokemonListQuery(name = Optional.present("%${name ?:""}%"))).execute()
            val data = response.dataOrThrow()
            data.pokemon.map {
                val speciesName = it.species_name.firstOrNull()?.name ?: ""
                Pokemon(
                    it.id,
                    speciesName,
                    PokemonImage(
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${it.id}.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.id}.png",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/${it.id}.png"
                    )
                )
            }
        }
    }

    override suspend fun getPokemonDetails(id: Int): PokemonDetails {
        return withContext(dispatcher) {
            val response = apolloClient.query(PokemonDetailQuery(Optional.present(id))).execute()
            val data = response.dataOrThrow()
            val pokemon = data.pokemon.firstOrNull() ?: throw IllegalStateException("Pokemon not found")
            val pokemonData = pokemon.pokemon_data.firstOrNull() ?: throw IllegalStateException("Pokemon data not found")
            val stats = pokemonData.stats.associateBy({ s -> Stats.values().first { it.id == s.id } }, {it.base_stat})
            val moves = pokemonData.moves.map {
                Move(
                    it.id,
                    it.metadata?.name?.firstOrNull()?.name?: "",
                    it.metadata?.accuracy?: -1,
                    it.metadata?.power?: -1,
                    MoveType.UNKNOWN,
                    MoveDamageType.UNKNOWN
                )
            }
            val abilities = pokemonData.abilities.map {
                Ability(
                    it.id,
                    it.metadata?.name?.firstOrNull()?.name?: "",
                    it.is_hidden,
                    it.metadata?.description?.firstOrNull()?.effect?: ""
                )
            }
            val types = pokemonData.types.map {
                PokemonType(it.id, it.metadata?.name?.firstOrNull()?.name?: "")
            }

            PokemonDetails(
                pokemon.id,
                pokemon.species_name.firstOrNull()?.name ?: "",
                PokemonImage(
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemon.id}.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/${pokemon.id}.png"
                ),
                stats,
                moves,
                abilities,
                types
            )
        }
    }

    override suspend fun getMoveList(name: String?): List<Move> {
        return withContext(dispatcher) {
            val response = apolloClient.query(MoveListQuery()).execute()
            val data = response.dataOrThrow()
            data.moves.map {
                val moveName = it.move_name.firstOrNull()?.name ?: ""
                Move(
                    it.id,
                    moveName,
                    it.accuracy?: -1,
                    it.power?: -1,
                    it.type_id?.toMoveType() ?: MoveType.UNKNOWN,
                    it.move_damage_class_id?.toMoveDamageType() ?: MoveDamageType.UNKNOWN
                )
            }
        }
    }

    override suspend fun getMoveDetails(id: Int): MoveDetails {
        return withContext(dispatcher) {
            val response = apolloClient.query(MoveDetailsQuery(Optional.present(id))).execute()
            val data = response.dataOrThrow()
            val move = data.moves.firstOrNull() ?: throw IllegalStateException("Moves not found")
            val moveName = move.move_name.firstOrNull()?.name ?: ""
            MoveDetails(
                move.id,
                moveName,
                move.accuracy?: -1,
                move.power?: -1,
                move.type_id?.toMoveType() ?: MoveType.UNKNOWN,
                move.move_damage_class_id?.toMoveDamageType() ?: MoveDamageType.UNKNOWN
            )
        }
    }
}