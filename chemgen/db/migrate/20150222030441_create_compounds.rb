class CreateCompounds < ActiveRecord::Migration
  def change
    create_table :compounds do |t|
      t.timestamps null: false
      t.text :inchi_code
      t.string :inchi_key
      t.text :smiles_string
      t.text :molecular_formula
      t.decimal :mass, :precision=>11, :scale=>5
      t.decimal :logp, :precision=>7, :scale=>5
      t.integer :b_rotn, :size=>2
      t.decimal :volume, :precision=>9, :scale=>5
      t.integer :lip_violation, :size=>1
      t.decimal :asa_p, :precision=>9, :scale=>5
      t.boolean :natural
    end
  end
end
