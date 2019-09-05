package com.produtos.apirest.resources;

import java.util.List;

import javax.persistence.GenerationType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.repository.ProdutoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.produtos.apirest.models.Produto;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Produtos")
//CrossOrigin serve para permitir que todos os domínios acessem a API se usar o *
//Para permitir somente algum domínio específico usa @CrossOrigin(origins="/http://<site>")
@CrossOrigin(origins="*")
public class ProdutoResource {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	//listar todos os itens
	@GetMapping("/produtos")
	@ApiOperation(value="Retorna uma lista de produtos")
	public List<Produto> listaProdutos(){
		return produtoRepository.findAll();
	}
	
	//listar itens específicos (no caso, depende do ID)
	@GetMapping("/produto/{id}")
	@ApiOperation(value="Retorna uma produto único")
	public Produto listaProdutoUnico(@PathVariable(value="id") long id){
		return produtoRepository.findById(id);
	}
	
	//Posta um determinado produto no banco. Devido ao "GenerationType.AUTO", os produtos autoincrementam o ID
	@PostMapping("/produto")
	@ApiOperation(value="Insere um produto")
	public Produto salvaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	//Deleta um produto
	@DeleteMapping("/produto")
	@ApiOperation(value="Deleta um produto")
	public void deletaProduto(@RequestBody Produto produto) {
		produtoRepository.delete(produto);
	}
	
	//Faz alterações nas informações do produto
	@PutMapping("/produto")
	@ApiOperation(value="Atualiza um produto")
	public Produto atualizaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}

}

