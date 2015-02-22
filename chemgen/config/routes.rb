Rails.application.routes.draw do
  root "pages#index"
  get "search" => "compounds#search"
  resources :molfiles, :only=>[:show]
end
