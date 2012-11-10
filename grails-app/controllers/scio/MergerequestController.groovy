package scio

import difflib.DiffRow
import difflib.DiffRowGenerator

class MergerequestController {
	
	def scioService

	def index() {
	}

	def resolve(String id){
		MergeRequest merge = safeGetMerge(id)

		Scio scio = merge.destination
		def original = scio.contentLines()
		def revised = merge.source.contentLines()

		DiffRowGenerator.Builder builder = new DiffRowGenerator.Builder();
		boolean sideBySide = true;
		builder.showInlineDiffs(!sideBySide);
		builder.columnWidth(120);
		DiffRowGenerator dfg = builder.build();
		List<DiffRow> diffRows = dfg.generateDiffRows(original, revised);

		[merge : merge, diffRows: diffRows]
	}
	
	def accept(String id){
		MergeRequest merge = safeGetMerge(id)
		scioService.acceptMergeRequest(merge.id)
		flash.message = "Merge accepted"
		redirect controller: "scio", action: "show", params: [id: merge.destination.id]
	}
	
	def reject(String id){
		MergeRequest merge = safeGetMerge(id)
		merge.reject()
		merge.save(failOnError: true)
		flash.message = "Merge rejected"
		redirect controller: "home", action: "user" 
	}
	
	private MergeRequest safeGetMerge(String id){
		if(id){
			try{
				Integer mergeId = id as Integer
				MergeRequest merge = MergeRequest.get(mergeId)
				if(merge != null){
					return merge
				}
			}catch(NumberFormatException e){}
		}
		throw new MergeRequestNotFoundException()
	}
}
